package com.example.demo.order.usecases.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderProductDTO {
    private Long orderNumber;
    private Long barCode;
    private Double price;
    private String productName;
    private Long quantity;
    private Long stockQuantity;
}
