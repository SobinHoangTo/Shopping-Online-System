package data;

import models.Enums.GeneralStatus;
import models.Entities.Category;
import DAL.Implements.CategoryDAO;
import DAL.Implements.ProductDAO;
import DAL.Interfaces.ICategoryDAO;
import DAL.Interfaces.IProductDAO;
import models.Entities.Product;

public class InitData {

    public final Category[] CATEGORY_LIST = {
        new Category("Electronics", GeneralStatus.ACTIVE),
        new Category("Books", GeneralStatus.ACTIVE),
        new Category("Clothing", GeneralStatus.ACTIVE),
        new Category("Home Appliances", GeneralStatus.ACTIVE)};

    public final Product[] PRODUCT_LIST = {
        new Product("The Catcher in the Rye", 10.99, "A classic novel about teenage rebellion and alienation.", "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1398034300i/5107.jpg", GeneralStatus.ACTIVE, 2),
        new Product("To Kill a Mockingbird", 12.50, "A novel about racial injustice and moral growth in the Deep South.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThQm8rW00NBdArSeDjdpDs-R4JbQaNhuki-w&s", GeneralStatus.ACTIVE, 2),
        new Product("1984", 14.99, "A dystopian novel about totalitarian surveillance and thought control.", "https://cdn.waterstones.com/bookjackets/large/9780/1410/9780141036144.jpg", GeneralStatus.ACTIVE, 2),
        new Product("The Great Gatsby", 11.25, "A story about the American dream and the excesses of the Jazz Age.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4qQBN1_VoeU45Xm0xI2W61TSvGNvzk2Alzg&s", GeneralStatus.ACTIVE, 2),
        new Product("Moby-Dick", 13.75, "A novel about obsession and revenge on the high seas.", "https://upload.wikimedia.org/wikipedia/vi/8/85/Moby_Dick-C%C3%A1_voi_tr%E1%BA%AFng_%28s%C3%A1ch%29.jpg", GeneralStatus.ACTIVE, 2)};

    public void SeedCategoryData() {
        ICategoryDAO categoryDAO = new CategoryDAO();
        for (Category category : CATEGORY_LIST) {
            categoryDAO.Create(category);
        }
    }

    public void SeedProductData() {
        IProductDAO productDAO = new ProductDAO();
        for (Product product : PRODUCT_LIST) {
            productDAO.Create(product);
        }
    }

    //TODO: Add seed data for others (Products, Users, ...)
    public static void main(String[] args) {
        InitData init = new InitData();
        init.SeedCategoryData();
        init.SeedProductData();
//        init.SeedUserData();
    }
}
