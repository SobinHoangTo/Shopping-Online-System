/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Interfaces;

import models.DTOs.DataPublics;
import models.DTOs.GeneralQueryParam;
import models.Entities.Product;

/**
 *
 * @author vdqvi
 */
public interface IProductDAO extends IDAO<Product> {

    DataPublics<Product> AllWithQuery(GeneralQueryParam queryParam, String status);
}
