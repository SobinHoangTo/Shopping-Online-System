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
import java.util.ArrayList;
import java.util.Arrays;
import models.Entities.Product;
import org.json.JSONObject;
import services.CartService;
import services.ProductServices;

/**
 *
 * @author vdqvi
 */
public class OrderConfirmServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] productIds = request.getParameterValues("productIds");
        if (productIds == null || productIds.length == 0) {
            response.sendRedirect("home");
            return;
        }

        int[] ids = Arrays.stream(productIds)
                .mapToInt(Integer::parseInt)
                .toArray();

        CartService cartService = new CartService();
        JSONObject cartItemsJson = cartService.getCartItemsFromCookies(request.getCookies());

        ProductServices productServices = new ProductServices();
        ArrayList<Product> products = productServices.GetAllByIds(ids);

        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += cartItemsJson.getInt(product.getId() + "") * product.getPrice();
        }

        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("productIds", ids);
        request.getRequestDispatcher("Views/orderConfirm.jsp").forward(request, response);
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
