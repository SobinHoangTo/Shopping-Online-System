/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Implements;

import data.DBContext;
import models.Entities.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    public void saveProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (name, price, description, image, status, category_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getImage() != null ? product.getImage() : "default-image.jpg"); // Default image if none provided
            stmt.setString(5, product.getStatus());
            stmt.setInt(6, product.getCategoryId());

            stmt.executeUpdate();
        }
    }

    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE products SET name = ?, price = ?, description = ?, image = ?, status = ?, categoryId = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getImage() != null ? product.getImage() : "default-image.jpg"); // Default image if none provided
            stmt.setString(5, product.getStatus());
            stmt.setInt(6, product.getCategoryId());
            stmt.setInt(7, product.getId());

            stmt.executeUpdate();
        }
    }

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

    public static void main(String[] args) throws SQLException {
        ProductDAO d = new ProductDAO();
        Product p = d.getProductById(4);
        System.out.println(p.getName());
    }
}
