/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Implements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Entity.Sample;
import DAL.Interfaces.ISampleDAO;

/**
 *
 * @author vdqvi
 */
public class SampleDAO extends GeneralDAO<Sample> implements ISampleDAO {

    @Override
    public Sample GetByResultSet(ResultSet rs) throws SQLException {
        return new Sample(rs.getInt("id"),
                rs.getString("title"));
    }

    @Override
    public ArrayList<Sample> All() {
        try {
            ArrayList<Sample> list = new ArrayList<>();
            String sql = "select * from sample";
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
    public Sample Read(int id) {
        try {
            String sql = "select * from sample where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Sample data = GetByResultSet(rs);
            rs.close();
            ps.close();
            return data;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return null;
        }
    }

    @Override
    public boolean Create(Sample entity) {
        try {
            String sql = "insert into sample (title) values (?) ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getTitle());
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
            String sql = "delete from sample where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(Sample entity) {
        try {
            String sql = "update sample set title=? where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getTitle());
            ps.setInt(2, entity.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return false;
        }
    }

    @Override
    public Sample ReadByTitle(String title) {
        try {
            String sql = "select * from sample where title=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            Sample data = GetByResultSet(rs);
            rs.close();
            ps.close();
            return data;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return null;
        }
    }
}
