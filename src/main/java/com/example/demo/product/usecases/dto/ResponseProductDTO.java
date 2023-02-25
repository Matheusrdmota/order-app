package com.example.demo.product.usecases.dto;

import com.example.demo.product.entities.ProductCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDTO {
    private Long id;
    private Long barCode;
    private Double price;
    private ProductCategoryEnum category;
    private String productName;
    private String description;
    private String urlImage;
    private Long stockQuantity;
}
