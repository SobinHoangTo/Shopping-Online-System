package services;

import DAL.Implements.OrderDAO;
import java.util.ArrayList;
import models.Entities.Order;

public class OrderServices {

    private OrderDAO orderDAO;

    public OrderServices() {
        orderDAO = new OrderDAO();
    }

    public ArrayList<Order> GetByUserId(int userId) {
        return orderDAO.GetByUserID(userId);
    }
}
