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
import models.DTOs.DataPublics;
import models.DTOs.GeneralQueryParam;
import models.Enums.GeneralStatus;
import services.ProductServices;

/**
 *
 * @author vdqvi
 */
public class ProductListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductServices productService = new ProductServices();
        int pageIndex = request.getParameter("pageIndex") != null ? Integer.parseInt(request.getParameter("pageIndex")) : 0;
        int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize")) : 10;
        GeneralQueryParam queryParam = new GeneralQueryParam(
                pageIndex,
                pageSize,
                request.getParameter("search"),
                request.getParameter("sortBy"),
                request.getParameter("sortOrder")
        );
        DataPublics products = productService.GetByQuery(queryParam, GeneralStatus.ACTIVE);
        request.setAttribute("products", products.data);
        request.setAttribute("count", products.count);
        request.getRequestDispatcher("Views/productList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
