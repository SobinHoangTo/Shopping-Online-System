package services;

import DAL.Implements.OrderDAO;
import DAL.Implements.OrderDetailDAO;
import java.util.ArrayList;
import models.Entities.Order;
import models.Entities.OrderDetail;
import models.Entities.Product;
import org.json.JSONObject;

public class OrderServices {

    private OrderDAO orderDAO;

    public OrderServices() {
        orderDAO = new OrderDAO();
    }

    public ArrayList<Order> GetByUserId(int userId) {
        return orderDAO.GetByUserID(userId);
    }

    public boolean Create(Order order) {
        return orderDAO.Create(order);
    }

    public boolean CreateOrderWithGHN(Order newOrder, int[] productIds, JSONObject cartItemsJson, String district, String ward) {
        try {
            GHNService _GHNService = new GHNService();
            ProductServices productServices = new ProductServices();
            ArrayList<Product> productList = productServices.GetAllByIds(productIds);
            JSONObject jsonResponse = _GHNService.CreateOrder(newOrder.getName(), newOrder.getPhone(), newOrder.getAddress(), ward, district, newOrder.getPaymentMethod(), newOrder.getAmount(), productList, cartItemsJson);
            if (jsonResponse == null) {
                throw new Exception("Fail to create order in GHN!");
            }
            newOrder.setTrackingNumber(jsonResponse.getJSONObject("data").getString("order_code"));
            Integer orderId = orderDAO.CreateReturnId(newOrder);
            if (orderId == null) {
                throw new Exception("Failed to create order in database");
            }
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            for (Product p : productList) {
                orderDetailDAO.Create(new OrderDetail(orderId, p.getId(), cartItemsJson.getInt(p.getId() + "")));
            }
            return true;
        } catch (Exception e) {
            System.err.println("Failed to create order due to: " + e.getMessage());
        }
        return false;

    }
    
    public Order GetOrderById(int id) {
        return orderDAO.Read(id);
    }
}
