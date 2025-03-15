package config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Admin
 */
public class CloudinaryConfig {
    private static final Logger LOGGER = Logger.getLogger(CloudinaryConfig.class.getName());
    private static final Cloudinary cloudinary;

    static {
        try {
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dnnbdiqdz",
                "api_key", "125368313514172",
                "api_secret", "KjP6OMO4bvxZ_ABoHf07oWAlQ0c",
                "secure", true
            ));
            if (cloudinary.uploader() == null) {
                throw new IllegalStateException("Cloudinary uploader not initialized");
            }
            LOGGER.log(Level.INFO, "Cloudinary initialized successfully");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize Cloudinary", e);
            throw new ExceptionInInitializerError("Cloudinary initialization failed: " + e.getMessage());
        }
    }

    private CloudinaryConfig() {
        throw new AssertionError("Utility class cannot be instantiated");
    }

    /**
     * Uploads an image to Cloudinary from a Part object.
     * @param imagePart The multipart file part containing the image
     * @return The secure URL of the uploaded image, or null if upload fails or no image provided
     * @throws IOException If an error occurs during upload
     */
    public static String uploadImage(Part imagePart) throws IOException {
        if (imagePart == null || imagePart.getSize() == 0) {
            LOGGER.log(Level.WARNING, "No image provided for upload");
            return null;
        }

        String contentType = imagePart.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            LOGGER.log(Level.WARNING, "Invalid file type: {0}", contentType);
            throw new IOException("Only image files are allowed");
        }

        String fileName = imagePart.getSubmittedFileName();
        if (fileName == null || fileName.trim().isEmpty()) {
            fileName = "default_image";
        } else {
            fileName = fileName.substring(0, fileName.lastIndexOf('.') != -1 ? fileName.lastIndexOf('.') : fileName.length());
        }

        byte[] fileContent;
        try (InputStream inputStream = imagePart.getInputStream();
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            byte[] data = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytesRead);
            }
            fileContent = buffer.toByteArray();
        }

        try {
            Map<String, Object> uploadParams = ObjectUtils.asMap(
                "resource_type", "image",
                "folder", "products",
                "public_id", "product_" + fileName + "_" + System.currentTimeMillis(),
                "overwrite", true
            );
            Map<String, Object> uploadResult = cloudinary.uploader().upload(fileContent, uploadParams);
            String secureUrl = (String) uploadResult.get("secure_url");
            LOGGER.log(Level.INFO, "Image uploaded successfully: {0}", secureUrl);
            return secureUrl;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to upload image to Cloudinary", e);
            throw new IOException("Failed to upload image to Cloudinary: " + e.getMessage(), e);
        }
    }

    /**
     * Gets the Cloudinary instance for advanced operations if needed.
     * @return The configured Cloudinary instance
     */
    public static Cloudinary getCloudinary() {
        return cloudinary;
    }
}