package DAL.Implements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import models.Entities.Category;
import DAL.Interfaces.ICategoryDAO;
import models.Enums.GeneralStatus;

public class CategoryDAO extends GeneralDAO<Category> implements ICategoryDAO {

    @Override
    public Category GetByResultSet(ResultSet rs) throws SQLException {
        return new Category(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("status"));
    }

    @Override
    public ArrayList<Category> All() {
        try {
            ArrayList<Category> list = new ArrayList<>();
            String sql = "select * from categories";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(GetByResultSet(rs));
            }
            rs.close();
            ps.close();
            return list;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Category Read(int id) {
        try {
            String sql = "select * from categories where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Category data = GetByResultSet(rs);
            rs.close();
            ps.close();
            return data;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return null;
        }
    }

    @Override
    public boolean Create(Category entity) {
        try {
            String sql = "insert into categories (name, status) values (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getName());
            ps.setString(2, GeneralStatus.ACTIVE);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(Category entity) {
        try {
            String sql = "update categories set name=? where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(int id) {
        try {
            String sql = "update from categories set status=? where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, GeneralStatus.INACTIVE);
            ps.setInt(2, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return false;
        }
    }

    @Override
    public Category GetByName(String name) {
        try {
            String sql = "select * from categories where name=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Category data = GetByResultSet(rs);
            rs.close();
            ps.close();
            return data;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return null;
        }
    }
}
