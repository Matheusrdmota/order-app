package com.example.demo.order.usecases.dto;

import com.example.demo.order.entities.PaymentType;
import com.example.demo.order.entities.Product;
import com.example.demo.order.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderDTO {
    private Long orderNumber;
    private BigDecimal orderValue;
    private String client;
    private LocalDateTime date;
    private Status status;
    private List<Product> products;
    private PaymentType paymentType;
    private BigDecimal discount;
}
