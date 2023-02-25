package com.example.demo.product.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private Long barCode;
    private Double price;
    private ProductCategoryEnum category;
    private String productName;
    private String description;
    private String urlImage;
    private Long stockQuantity;

    public boolean priceAndBarCodeIsValid(){
        return price > 0 && barCode > 0 && stockQuantity >= 0;
    }

    public boolean verifyIfHasStock(){ return stockQuantity > 0; }
}
