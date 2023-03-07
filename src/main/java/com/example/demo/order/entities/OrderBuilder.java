package com.example.demo.order.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderBuilder {
    private Order order;

    public static OrderBuilder Builder(){
        return new OrderBuilder();
    }

    public OrderBuilder withClient(String client){
        this.order.setClient(client);
        return this;
    }

    public OrderBuilder withPaymentType(PaymentType paymentType){
        this.order.setPaymentType(paymentType);
        return this;
    }

    public OrderBuilder withDiscount(BigDecimal discount){
        this.order.setDiscount(discount);
        return this;
    }

    public Order build(){
        order.setStatus(Status.PENDENTE);
        order.setOrderValue(BigDecimal.ZERO);
        order.setProducts(new ArrayList<>());
        order.setDate(LocalDateTime.now());
        return this.order;
    }
}
