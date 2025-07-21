package DAL.Interfaces;

import java.util.ArrayList;
import models.Entities.OrderDetail;

public interface IOrderDetailDAO {
    ArrayList<OrderDetail> GetByOrderId (int orderId);
}
