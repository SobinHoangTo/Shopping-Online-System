/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import DAL.Implements.ProductDAO;
import java.util.ArrayList;
import java.util.HashSet;
import models.DTOs.DataPublics;
import models.DTOs.GeneralQueryParam;
import models.Entities.Product;

/**
 *
 * @author vdqvi
 */
public class ProductServices {

    private ProductDAO productDAO;

    public ProductServices() {
        productDAO = new ProductDAO();
    }

    public DataPublics<Product> GetByQuery(GeneralQueryParam queryParam, String status) {
        return productDAO.AllWithQuery(queryParam, status);
    }

    public Product GetById(int id) {
        return productDAO.Read(id);
    }

    public ArrayList<Product> GetAllByIds(int[] ids) {
        ArrayList<Product> list = new ArrayList<>();
        HashSet<Integer> uniqueIds = new HashSet<>();

        for (int id : ids) {
            if (uniqueIds.add(id)) {
                list.add(productDAO.Read(id));
            }
        }
        return list;
    }

    public double GetTotalPrice(ArrayList<Product> data, ArrayList<Integer> quantityList) {
        if (data.size() != quantityList.size()) {
            return 0;
        }
        double totalPrice = 0;
        for (int i = 0; i < data.size(); i++) {
            totalPrice += data.get(i).getPrice() * quantityList.get(i);
        }
        return totalPrice;
    }
}
