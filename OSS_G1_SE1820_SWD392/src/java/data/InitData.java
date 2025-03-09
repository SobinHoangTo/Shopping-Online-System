package data;

import java.sql.PreparedStatement;
import model.Category;

public class InitData extends DBContext {

    public final Category[] CATEGORY_LIST = {
        new Category("Toy", "Active"),
        new Category("Jewelry", "Active"),
    };
            
    public void SeedCategoryData() {
        for (Category cate : CATEGORY_LIST) {
            try {
                String sql = "insert into category (name) values (?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, cate.getName());
                ps.executeUpdate();
            } catch (Exception a) {
                System.err.println(a.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        InitData init = new InitData();
        init.SeedCategoryData();
    }
}
