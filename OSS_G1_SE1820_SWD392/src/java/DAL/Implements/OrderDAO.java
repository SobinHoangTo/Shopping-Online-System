package DAL.Implements;

import DAL.Interfaces.IOrderDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Entities.Order;
import java.sql.PreparedStatement;
import models.Enums.GeneralStatus;
import models.Enums.ShippingStatus;
import models.Enums.TableNames;

public class OrderDAO extends GeneralDAO<Order> implements IOrderDAO {

    @Override
    public Order GetByResultSet(ResultSet rs) throws SQLException {
        return new Order(rs.getInt("id"),
                rs.getInt("userId"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("name"),
                rs.getString("paymentMethod"),
                rs.getString("paymentStatus"),
                rs.getString("shippingStatus"),
                rs.getString("status"),
                rs.getString("trackingNumber"),
                rs.getDouble("amount"));
    }

    public ArrayList<Order> All() {
        return super.All(TableNames.ORDER);
    }

    public Order Read(int id) {
        return super.Read(TableNames.ORDER, id);
    }

    @Override
    public boolean Create(Order entity) {
        try {
            String sql = "INSERT INTO [dbo].[orders]\n"
                    + "           ([userId]\n"
                    + "           ,[address]\n"
                    + "           ,[phone]\n"
                    + "           ,[name]\n"
                    + "           ,[paymentMethod]\n"
                    + "           ,[paymentStatus]\n"
                    + "           ,[shippingStatus]\n"
                    + "           ,[status]\n"
                    + "           ,[trackingNumber]\n"
                    + "           ,[amount]) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getUserId());
            ps.setString(2, entity.getAddress());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getName());
            ps.setString(5, entity.getPaymentMethod());
            ps.setString(6, entity.getPaymentStatus());
            ps.setString(7, entity.getShippingStatus());
            ps.setString(8, entity.getStatus());
            ps.setString(9, entity.getTrackingNumber());
            ps.setDouble(10, entity.getAmount());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(Order entity) {
        try {
            String sql = "[dbo].[orders]\n"
                    + "   SET [userId] = ?\n"
                    + "      ,[address] = ?\n"
                    + "      ,[phone] = ?\n"
                    + "      ,[name] = ?\n"
                    + "      ,[paymentMethod] = ?\n"
                    + "      ,[paymentStatus] = ?\n"
                    + "      ,[shippingStatus] = ?\n"
                    + "      ,[status] = ?\n"
                    + "      ,[trackingNumber] = ?\n"
                    + "      ,[amount] = ?\n"
                    + "	WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getUserId());
            ps.setString(2, entity.getAddress());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getName());
            ps.setString(5, entity.getPaymentMethod());
            ps.setString(6, entity.getPaymentStatus());
            ps.setString(7, entity.getShippingStatus());
            ps.setString(8, entity.getStatus());
            ps.setString(9, entity.getTrackingNumber());
            ps.setDouble(10, entity.getAmount());
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
            String sql = "update from orders set status=? where id=?";
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
    public ArrayList<Order> GetByUserID(int userId) {
        try {
            ArrayList<Order> list = new ArrayList<>();
            String sql = "select * from " + TableNames.ORDER + " where userId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
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
}
