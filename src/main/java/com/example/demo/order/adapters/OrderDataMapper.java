package com.example.demo.order.adapters;

import com.example.demo.order.entities.PaymentType;
import com.example.demo.order.entities.Product;
import com.example.demo.order.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDataMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long orderNumber;
    private BigDecimal orderValue;
    private String client;
    private LocalDateTime date;
    private Status status;
    @ElementCollection
    private List<String> products;
    private PaymentType paymentType;
    private BigDecimal discount;
}
