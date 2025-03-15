package controllers.Clients;

import DAL.Implements.ProductDAO;
import DAL.Implements.CategoryDAO;
import config.CloudinaryConfig;
import models.Entities.Product;
import models.Entities.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
@MultipartConfig(maxFileSize = 10 * 1024 * 1024) 
public class ProductServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            List<Category> categories = categoryDAO.All();
            if (categories == null || categories.isEmpty()) {
                LOGGER.log(Level.WARNING, "No categories found");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No categories available");
                return;
            }

            String productIdRaw = request.getParameter("productId");
            int productId = 0;
            try {
                if (productIdRaw != null && !productIdRaw.trim().isEmpty()) {
                    productId = Integer.parseInt(productIdRaw);
                }
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Invalid productId format: {0}", productIdRaw);
            }

            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(productId != 0 ? productId : 4);
            if (product == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }

            request.setAttribute("product", product);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/Views/editProduct.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in doGet", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching product details: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String action = request.getParameter("action");
            if (!"update".equals(action)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                return;
            }

            String productIdStr = request.getParameter("productId");
            String name = request.getParameter("productName");
            String priceStr = request.getParameter("price");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            String categoryIdStr = request.getParameter("category");

            if (productIdStr == null || name == null || priceStr == null || status == null || categoryIdStr == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required fields");
                return;
            }

            int productId;
            double price;
            int categoryId;
            try {
                productId = Integer.parseInt(productIdStr.trim());
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
                    LOGGER.log(Level.WARNING, "Image upload failed but continuing with update");
                }
            }

            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(productId);
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

            response.sendRedirect("ProductServlet?productId=" + productId);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO error in doPost", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating product: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error in doPost", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error: " + e.getMessage());
        }
    }
}
