package DAL.Implements;

import DAL.Interfaces.IOrderDetailDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Entities.OrderDetail;
import models.Enums.GeneralStatus;
import models.Enums.TableNames;

public class OrderDetailDAO extends GeneralDAO<OrderDetail> implements IOrderDetailDAO{

    @Override
    public OrderDetail GetByResultSet(ResultSet rs) throws SQLException {
        return new OrderDetail(rs.getInt("id"),
                rs.getInt("orderId"),
                rs.getInt("productID"),
                rs.getInt("quantity"));
    }
    
    public ArrayList<OrderDetail> All() {
        return super.All(TableNames.ORDER_DETAIL);
    }
    
    public OrderDetail Read(int id) {
        return super.Read(TableNames.ORDER_DETAIL, id);
    }

    @Override
    public boolean Create(OrderDetail entity) {
        try {
            String sql = "INSERT INTO [dbo].[" + TableNames.ORDER_DETAIL +  "]\n"
                    + "           ([orderId]\n"
                    + "           ,[productID]\n"
                    + "           ,[quantity]) values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getOrderId());
            ps.setInt(2, entity.getProductId());
            ps.setInt(3, entity.getQuantity());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception a) {
            System.err.println(a.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(OrderDetail entity) {
        try {
            String sql = "[dbo].[" + TableNames.ORDER_DETAIL +  "]\n"
                    + "   SET [orderId] = ?\n"
                    + "      ,[productID] = ?\n"
                    + "      ,[quantity] = ?\n"
                    + "	WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getOrderId());
            ps.setInt(2, entity.getProductId());
            ps.setInt(3, entity.getQuantity());
            ps.setInt(4, entity.getId());
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
            String sql = "update from " + TableNames.ORDER_DETAIL + " set status=? where id=?";
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
    
}
