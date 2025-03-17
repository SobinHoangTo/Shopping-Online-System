package models.DTOs;

import models.Entities.Product;

public class ProductInOrderDTO {
    private Product product;
    private int quantity;

    public ProductInOrderDTO(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
