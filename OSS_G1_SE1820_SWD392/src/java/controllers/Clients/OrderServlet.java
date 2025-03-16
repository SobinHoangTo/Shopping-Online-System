/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.Clients;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import models.Entities.Order;
import models.Enums.GeneralStatus;
import models.Enums.PaymentMethod;
import models.Enums.PaymentStatus;
import models.Enums.ShippingStatus;
import org.json.JSONObject;
import services.CartService;
import services.OrderServices;

/**
 *
 * @author vdqvi
 */
public class OrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String address = request.getParameter("address");
        String totalPrice = request.getParameter("totalPrice");
        String paymentMethod = request.getParameter("paymentMethod");

        String[] productIdsString = request.getParameterValues("productIds");
        int[] productIds = Arrays.stream(productIdsString)
                .mapToInt(Integer::parseInt)
                .toArray();

        CartService cartService = new CartService();
        JSONObject cartItemsJson = cartService.getCartItemsFromCookies(request.getCookies());

        Order newOrder = new Order(null, address, phone, name, paymentMethod, PaymentStatus.UNPAID, ShippingStatus.PICKING, GeneralStatus.ACTIVE, null, (int) Double.parseDouble(totalPrice));

        try {
            switch (paymentMethod) {
                case PaymentMethod.COD:
                    OrderServices orderServices = new OrderServices();
                    orderServices.CreateOrderWithGHN(newOrder, productIds, cartItemsJson, district, ward);
                    response.sendRedirect("home");
                    return;
                case PaymentMethod.ONLINE_PAYMENT:
                    break;
                case PaymentMethod.BANK_TRANSFER:
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
