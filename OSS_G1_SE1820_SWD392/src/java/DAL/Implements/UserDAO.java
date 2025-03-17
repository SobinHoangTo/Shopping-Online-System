/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Implements;

import DAL.Interfaces.IUserDAO;
import jakarta.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Entities.User;

/**
 *
 * @author Lenono
 */
public class UserDAO extends GeneralDAO<User> implements IUserDAO{
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    
    @Override
    public boolean Create(User entity) {
        String sql = "INSERT INTO [dbo].[users]\n"
                + "     VALUES (?,?,?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getFullname());
            ps.setString(4, entity.getRole());
           
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }
    
    @Override
    public boolean Update(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User findUserByEmail(String email) {
        
        String query = "select * from users\n"
                + "where [email] = ? ";
                
        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, email);
          
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    
    }

    @Override
    public User GetByResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
