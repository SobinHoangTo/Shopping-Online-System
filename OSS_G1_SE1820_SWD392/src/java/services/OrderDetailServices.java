package services;

import DAL.Implements.OrderDetailDAO;
import java.util.ArrayList;
import models.Entities.OrderDetail;

public class OrderDetailServices {
private OrderDetailDAO orderDetailDAO;

    public OrderDetailServices() {
        orderDetailDAO = new OrderDetailDAO();
    }
    
    public ArrayList<OrderDetail> GetByOrderId(int orderId) {
        return orderDetailDAO.GetByOrderId(orderId);
    }
    
}
