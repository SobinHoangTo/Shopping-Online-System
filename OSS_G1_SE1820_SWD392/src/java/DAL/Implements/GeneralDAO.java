/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Implements;

import DAL.Interfaces.IDAO;
import data.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author vdqvi
 */
public abstract class GeneralDAO<T> extends DBContext implements IDAO<T> {

    @Override
    public ArrayList<T> All(String tableName) {
        try {
            ArrayList<T> list = new ArrayList<>();
            String sql = "select * from " + tableName;
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
    public T Read(String tableName, int id) {
        try {
            String sql = "select * from " + tableName + " where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                T data = GetByResultSet(rs);
                rs.close();
                ps.close();
                return data;
            }
            rs.close();
            ps.close();
        } catch (Exception a) {
            System.err.println(a.getMessage());
        }
        return null;
    }

}
