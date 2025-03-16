package DAL.Implements;

import DAL.Interfaces.IProductDAO;
import data.DBContext;
import models.Entities.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DBContext implements IProductDAO {

    @Override
    public void saveProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (name, price, description, image, status, categoryId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getImage() != null ? product.getImage() : "default-image.jpg");
            stmt.setString(5, product.getStatus());
            stmt.setInt(6, product.getCategoryId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE products SET name = ?, price = ?, description = ?, image = ?, status = ?, categoryId = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getImage() != null ? product.getImage() : "default-image.jpg");
            stmt.setString(5, product.getStatus());
            stmt.setInt(6, product.getCategoryId());
            stmt.setInt(7, product.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Product getProductById(int id) throws SQLException {
        String query = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("status"),
                        rs.getInt("categoryId")
                );
            }
        }
        return null;
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("status"),
                        rs.getInt("categoryId")
                ));
            }
        }
        return products;
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE categoryId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("status"),
                        rs.getInt("category_id")
                ));
            }
        }
        return products;
    }
    
    public static void main(String[] args) throws SQLException {
        ProductDAO d = new ProductDAO();
        Product p = d.getProductById(1);
        System.out.println(p.getId());
    }
}