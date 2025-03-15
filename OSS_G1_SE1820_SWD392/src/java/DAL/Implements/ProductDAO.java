/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Implements;

import DAL.Interfaces.IProductDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.DTOs.DataPublics;
import models.DTOs.GeneralQueryParam;
import models.Entities.Product;
import models.Enums.TableNames;

/**
 *
 * @author vdqvi
 */
public class ProductDAO extends GeneralDAO<Product> implements IProductDAO {

    @Override
    public Product GetByResultSet(ResultSet rs) throws SQLException {
        return new Product(rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("description"),
                rs.getString("image"),
                rs.getString("status"),
                rs.getInt("categoryId")
        );
    }

    public ArrayList<Product> All() {
        return super.All(TableNames.PRODUCT);
    }

    public Product Read(int id) {
        return super.Read(TableNames.PRODUCT, id);
    }

    @Override
    public boolean Create(Product entity) {
        try {
            String sql = "insert into " + TableNames.PRODUCT + " (name, price, description, image, categoryId, status) values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getName());
            ps.setDouble(2, entity.getPrice());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getImage());
            ps.setInt(5, entity.getCategoryId());
            ps.setString(6, entity.getStatus());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(Product entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DataPublics<Product> AllWithQuery(GeneralQueryParam queryParam, String status) {
        try {
            ArrayList<Product> list = new ArrayList<>();
            
            String countSql = "select COUNT(*) from " + TableNames.PRODUCT + " where name like ? ";
            String sql = "select * from " + TableNames.PRODUCT + " where name like ? ";
            
            if (status != null) {
                countSql += "and status='" + status + "' ";
                sql += "and status='" + status + "' ";
            }
            if (queryParam.sortBy != null && queryParam.sortOrder != null) {
                sql += "order by " + queryParam.sortBy + " " + queryParam.sortOrder + " ";
            } else {
                sql += "order by id ";
            }
            sql += " offset ? rows fetch next ? rows only";
            
            String search = "%" + (queryParam.search != null ? queryParam.search : "") + "%";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, search);
            ps.setInt(2, queryParam.pageSize * queryParam.pageIndex);
            ps.setInt(3, queryParam.pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(GetByResultSet(rs));
            }

            ps = connection.prepareStatement(countSql);
            ps.setString(1, search);
            rs = ps.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            ps.close();
            return new DataPublics(list, count);
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return new DataPublics(new ArrayList<Product>(), 0);
        }
    }
}
