package com.example.demo.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long orderNumber;
    private Long barCode;
    private Double price;
    private String productName;
    private Long quantity;
    private Long stockQuantity;

    public boolean productHasStock(){
        return this.stockQuantity > 0 && this.quantity <= stockQuantity;
    }
}
