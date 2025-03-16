package controllers.Clients;

import DAL.Implements.CategoryDAO;
import DAL.Implements.ProductDAO;
import config.CloudinaryConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Entities.Category;
import models.Entities.Product;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
@MultipartConfig(maxFileSize = 10 * 1024 * 1024) // Max file size: 10MB
public class ProductServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy danh sách các danh mục
            CategoryDAO categoryDAO = new CategoryDAO();
            List<Category> categories = categoryDAO.All();
            if (categories == null || categories.isEmpty()) {
                LOGGER.log(Level.WARNING, "No categories found");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No categories available");
                return;
            }

            // Lấy productId từ request
            String productIdRaw = request.getParameter("productId");
            Product product = null;
            if (productIdRaw != null && !productIdRaw.trim().isEmpty()) {
                try {
                    int productId = Integer.parseInt(productIdRaw);
                    ProductDAO productDAO = new ProductDAO();
                    product = productDAO.getProductById(productId);  // Lấy thông tin sản phẩm theo ID
                    if (product == null) {
                        LOGGER.log(Level.WARNING, "Product not found for ID: {0}", productId);
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                        return;
                    }
                } catch (NumberFormatException e) {
                    LOGGER.log(Level.WARNING, "Invalid productId format: {0}", productIdRaw);
                }
            }

            // Đưa danh mục và sản phẩm vào request
            request.setAttribute("categories", categories);
            request.setAttribute("product", product);  // Nếu là thêm mới, product sẽ là null
            request.getRequestDispatcher("/Views/product.jsp").forward(request, response);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in doGet", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading page: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String action = request.getParameter("action");
            String productIdStr = request.getParameter("productId");
            String name = request.getParameter("productName");
            String priceStr = request.getParameter("price");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            String categoryIdStr = request.getParameter("category");

            if (name == null || priceStr == null || status == null || categoryIdStr == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required fields");
                return;
            }

            if (!"save".equals(action) && !"update".equals(action)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                return;
            }

            int productId = 0;
            double price;
            int categoryId;
            try {
                if (productIdStr != null && !productIdStr.trim().isEmpty()) {
                    productId = Integer.parseInt(productIdStr.trim());
                }
                price = Double.parseDouble(priceStr.trim());
                categoryId = Integer.parseInt(categoryIdStr.trim());
            } catch (NumberFormatException e) {
                LOGGER.log(Level.SEVERE, "Error parsing numeric values", e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric input");
                return;
            }

            Part imagePart = request.getPart("productImages");
            String imageUrl = null;
            if (imagePart != null && imagePart.getSize() > 0) {
                imageUrl = CloudinaryConfig.uploadImage(imagePart);
                if (imageUrl == null) {
                    LOGGER.log(Level.WARNING, "Image upload failed but continuing with operation");
                }
            }

            ProductDAO productDAO = new ProductDAO();
            Product product;

            if ("save".equals(action)) {
                // Add new product
                product = new Product(
                        name.trim(),
                        price,
                        description != null ? description.trim() : "",
                        imageUrl != null ? imageUrl : "default-image.jpg",
                        status.trim(),
                        categoryId
                );
                productDAO.saveProduct(product);
                LOGGER.log(Level.INFO, "Product saved successfully: {0}", name);
                request.setAttribute("message", "Product added successfully!");
            } else {
                // Update existing product
                product = productDAO.getProductById(productId);
                if (product == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                    return;
                }
                product.setName(name.trim());
                product.setPrice(price);
                product.setDescription(description != null ? description.trim() : "");
                product.setStatus(status.trim());
                product.setCategoryId(categoryId);
                if (imageUrl != null) {
                    product.setImage(imageUrl);
                }
                productDAO.updateProduct(product);
                LOGGER.log(Level.INFO, "Product updated successfully: ID={0}", productId);
                request.setAttribute("message", "Product updated successfully!");
            }

            // Reload categories and forward to the same page
            CategoryDAO categoryDAO = new CategoryDAO();
            request.setAttribute("categories", categoryDAO.All());
            request.setAttribute("product", product);
            request.getRequestDispatcher("/Views/product.jsp").forward(request, response);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in doPost", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for managing products (add and edit)";
    }
}
