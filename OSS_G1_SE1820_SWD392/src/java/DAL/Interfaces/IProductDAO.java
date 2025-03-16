/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Interfaces;

import models.Entities.Product;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Product Data Access Object (DAO) operations.
 * Defines methods for interacting with the Product entity in the database.
 * 
 * @author Admin
 */
public interface IProductDAO {

    /**
     * Saves a new product to the database.
     * 
     * @param product The Product object to be saved.
     * @throws SQLException If a database access error occurs.
     */
    void saveProduct(Product product) throws SQLException;

    /**
     * Updates an existing product in the database.
     * 
     * @param product The Product object with updated details.
     * @throws SQLException If a database access error occurs.
     */
    void updateProduct(Product product) throws SQLException;

    /**
     * Retrieves a product from the database by its ID.
     * 
     * @param id The ID of the product to retrieve.
     * @return The Product object if found, null otherwise.
     * @throws SQLException If a database access error occurs.
     */
    Product getProductById(int id) throws SQLException;

    /**
     * Deletes a product from the database by its ID.
     * 
     * @param id The ID of the product to delete.
     * @throws SQLException If a database access error occurs.
     */
    void deleteProduct(int id) throws SQLException;

    /**
     * Retrieves all products from the database.
     * 
     * @return A List of Product objects.
     * @throws SQLException If a database access error occurs.
     */
    List<Product> getAllProducts() throws SQLException;

    /**
     * Retrieves all products belonging to a specific category.
     * 
     * @param categoryId The ID of the category.
     * @return A List of Product objects in the specified category.
     * @throws SQLException If a database access error occurs.
     */
    List<Product> getProductsByCategory(int categoryId) throws SQLException;
}