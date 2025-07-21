package controllers.Customer;

import DAL.Implements.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.DTOs.ProductInOrderDTO;
import models.Entities.Order;
import models.Entities.OrderDetail;
import models.Entities.Product;
import org.json.JSONObject;
import services.GHNService;
import services.OrderDetailServices;
import services.OrderServices;
import services.ProductServices;

public class OrderDetailServlet extends HttpServlet {

    OrderDetailServices orderDetailService = new OrderDetailServices();
    OrderServices orderService = new OrderServices();
    ProductServices productServices = new ProductServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("id"));

        ArrayList<OrderDetail> listOrderDetail = orderDetailService.GetByOrderId(orderId);
        ArrayList<Product> listProductInOrder = new ArrayList<>();
        for (OrderDetail o : listOrderDetail) {
            listProductInOrder.add(productServices.GetById(o.getProductId()));
        }


        Order orderInfor = orderService.GetOrderById(orderId);
        GHNService ghnService = new GHNService();
        JSONObject ghnTracking = ghnService.GetOrderStatus(orderInfor.getTrackingNumber());  // getTrackingNumber = mã đơn hàng GHN

        String deliveryStatus = "Unknown";
        String receiverName = "";
        String receiverPhone = "";
        String receiverAddress = "";

        if (ghnTracking != null && ghnTracking.has("data")) {
            JSONObject data = ghnTracking.getJSONObject("data");
            deliveryStatus = data.getString("status"); // Trạng thái giao hàng (e.g., "delivering", "delivered", etc.)
            receiverName = data.optString("to_name", "");
            receiverPhone = data.optString("to_phone", "");
            receiverAddress = data.optString("to_address", "");
        }

//        String deliveryStatusVN = mapGHNStatusToVietnamese(deliveryStatus);
//        request.setAttribute("deliveryStatusVN", deliveryStatusVN);
        request.setAttribute("deliveryStatus", deliveryStatus);
        request.setAttribute("receiverName", receiverName);
        request.setAttribute("receiverPhone", receiverPhone);
        request.setAttribute("receiverAddress", receiverAddress);

        request.setAttribute("orderInfor", orderInfor);
        request.setAttribute("listOrderDetail", listOrderDetail);
        request.setAttribute("listProduct", listProductInOrder);
        request.getRequestDispatcher("Views/Customer/OrderDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private String mapGHNStatusToVietnamese(String statusCode) {
        switch (statusCode) {
            case "ready_to_pick":
                return "Sẵn sàng để lấy hàng";
            case "picking":
                return "Đang lấy hàng";
            case "money_collect_picking":
                return "Đang thu tiền khi lấy hàng";
            case "picked":
                return "Đã lấy hàng";
            case "storing":
                return "Đang lưu kho";
            case "transporting":
                return "Đang vận chuyển";
            case "delivering":
                return "Đang giao hàng";
            case "delivered":
                return "Đã giao hàng";
            case "delivery_fail":
                return "Giao hàng thất bại";
            case "returned":
                return "Đã trả hàng";
            case "cancel":
                return "Đã hủy";
            default:
                return "Không xác định";
        }
    }

}
