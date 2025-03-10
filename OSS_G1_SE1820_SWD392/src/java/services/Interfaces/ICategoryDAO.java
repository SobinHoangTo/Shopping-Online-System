package services.Interfaces;

import model.Category;

public interface ICategoryDAO extends IDAO<Category> {

    Category GetByName(String name);
}
