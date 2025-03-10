package data;

import models.Enums.GeneralStatus;
import models.Entities.Category;
import DAL.Implements.CategoryDAO;
import DAL.Interfaces.ICategoryDAO;

public class InitData {

    public final Category[] CATEGORY_LIST = {
        new Category("Electronics", GeneralStatus.ACTIVE),
        new Category("Books", GeneralStatus.ACTIVE),
        new Category("Clothing", GeneralStatus.ACTIVE),
        new Category("Home Appliances", GeneralStatus.ACTIVE)};

    public void SeedCategoryData() {
        ICategoryDAO categoryDAO = new CategoryDAO();
        for (Category category : CATEGORY_LIST) {
            categoryDAO.Create(category);
        }
    }

    //TODO: Add seed data for others (Products, Users, ...)
    
    public static void main(String[] args) {
        InitData init = new InitData();
        init.SeedCategoryData();
//        init.SeedUserData();
//        init.SeedProductData();
    }
}
