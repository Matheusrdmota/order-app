package com.example.demo.product.entities;

public class ProductFactory {
    public Product createProduct(Long barCode, Double price, ProductCategoryEnum category,
                                       String name, String description, String urlImage, Long stockQuantity){

        return new Product(barCode, price, category, name, description, urlImage, stockQuantity);
    }
}
