package com.example.demo.order.usecases.interactorsImpl;

import com.example.demo.order.entities.Order;
import com.example.demo.order.entities.OrderBuilder;
import com.example.demo.order.entities.Status;
import com.example.demo.order.usecases.dto.RequestOrderDTO;
import com.example.demo.order.usecases.dto.ResponseOrderDTO;
import com.example.demo.order.usecases.gateways.OrderGateway;
import com.example.demo.order.usecases.interactors.CreateOrderInteractor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreateOrderInteractorImpl implements CreateOrderInteractor {

    private OrderGateway gateway;

    public CreateOrderInteractorImpl(OrderGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public ResponseOrderDTO createOrder(RequestOrderDTO requestOrderDTO) {
        if(this.gateway.findOrderByNumber(requestOrderDTO.getOrderNumber()) != null){
            throw new IllegalArgumentException("Numero de pedido ja existe!");
        }

        Order order = OrderBuilder.Builder()
                .withClient(requestOrderDTO.getClient())
                .withPaymentType(requestOrderDTO.getPaymentType())
                .build();

        if(!requestOrderDTO.getDiscount().equals(BigDecimal.ZERO)) {
            order.setDiscount(requestOrderDTO.getDiscount());
        }else{
            order.setDiscountIfPaymentTypeIsDinheiroOrPix();
            requestOrderDTO.setDiscount(order.getDiscount());
        }

        if(!order.isValidDiscount()){
            throw new IllegalArgumentException("porcentagem de desconto inválida!");
        }

        return this.gateway.create(requestOrderDTO);
    }
}
