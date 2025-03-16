package data;

import models.Enums.GeneralStatus;
import models.Entities.Category;
import models.Entities.Product;
import DAL.Implements.CategoryDAO;
import DAL.Implements.ProductDAO;
import DAL.Interfaces.ICategoryDAO;
import DAL.Interfaces.IProductDAO;

public class InitData {

    public final Category[] CATEGORY_LIST = {
        new Category("Electronics", GeneralStatus.ACTIVE),
        new Category("Books", GeneralStatus.ACTIVE),
        new Category("Clothing", GeneralStatus.ACTIVE),
        new Category("Home Appliances", GeneralStatus.ACTIVE)
    };

    public final Product[] PRODUCT_LIST = {
        new Product(1, "Smartphone", 599.99, "Latest model with advanced features", "smartphone.jpg", GeneralStatus.ACTIVE, 1), // Electronics
        new Product(2, "Java Programming Book", 29.99, "Comprehensive guide to Java", "java-book.jpg", GeneralStatus.ACTIVE, 2), // Books
        new Product(3, "T-Shirt", 19.99, "100% cotton casual t-shirt", "tshirt.jpg", GeneralStatus.ACTIVE, 3), // Clothing
        new Product(4, "Blender", 49.99, "High-power kitchen blender", "blender.jpg", GeneralStatus.ACTIVE, 4) // Home Appliances
    };

    public void SeedCategoryData() {
        ICategoryDAO categoryDAO = new CategoryDAO();
        for (Category category : CATEGORY_LIST) {
            categoryDAO.Create(category);
        }
    }

    public void SeedProductData() {
        IProductDAO productDAO = new ProductDAO();
        for (Product product : PRODUCT_LIST) {
            try {
                productDAO.saveProduct(product);
            } catch (Exception e) {
                System.err.println("Error seeding product: " + product.getName() + " - " + e.getMessage());
            }
        }
    }

    //TODO: Add seed data for others (Users, ...)
    
    public static void main(String[] args) {
        InitData init = new InitData();
        init.SeedCategoryData();
        init.SeedProductData();
        // init.SeedUserData();
    }
}