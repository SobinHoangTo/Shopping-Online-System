/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.utils;

import DAL.Implements.ProductDAO;
import DAL.Interfaces.IProductDAO;
import models.DTOs.DataPublics;
import models.DTOs.GeneralQueryParam;
import models.Entities.Product;

/**
 *
 * @author vdqvi
 */
public class ProductServices {
    private IProductDAO productDAO;

    public ProductServices() {
        productDAO = new ProductDAO();
    }
    
    public DataPublics<Product> GetByQuery(GeneralQueryParam queryParam, String status){
        return productDAO.AllWithQuery(queryParam, status);
    }
}
