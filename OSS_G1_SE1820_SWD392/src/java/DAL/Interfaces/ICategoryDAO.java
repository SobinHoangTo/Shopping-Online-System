package DAL.Interfaces;

import Model.Entity.Category;

public interface ICategoryDAO extends IDAO<Category> {

    Category GetByName(String name);
}
