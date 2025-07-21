package DAL.Interfaces;

import models.Entities.Category;

public interface ICategoryDAO extends IDAO<Category> {

    Category GetByName(String name);
}
