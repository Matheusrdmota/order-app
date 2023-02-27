package com.example.demo.order.usecases.interactors;

import com.example.demo.order.entities.Order;
import com.example.demo.order.entities.Status;
import com.example.demo.order.usecases.dto.ResponseOrderDTO;
import com.example.demo.order.usecases.gateways.OrderGateway;

public abstract class UpdateOrderStatusInteractor {
    private OrderGateway gateway;
    protected ResponseOrderDTO orderDTO;

    public UpdateOrderStatusInteractor(OrderGateway gateway){
        this.gateway = gateway;
    }
    //TEMPLATE_METHOD
    protected void checkIfOrderExists(Long orderNumber){
        this.orderDTO = this.gateway.findOrderByNumber(orderNumber);

        if(orderDTO == null){
            throw new RuntimeException("Pedido nao encontrado!");
        }
    }

    protected Order convertToOrderEntity(ResponseOrderDTO orderDTO){
        Order order = new Order();

        order.setClient(orderDTO.getClient());
        order.setPaymentType(orderDTO.getPaymentType());
        order.setStatus(orderDTO.getStatus());
        order.setOrderValue(orderDTO.getOrderValue());
        order.setDiscount(orderDTO.getDiscount());
        order.setDate(orderDTO.getDate());
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setProducts(orderDTO.getProducts());

        return order;
    }

    protected void saveOrderUpdated(Order order){
        orderDTO.setClient(order.getClient());
        orderDTO.setPaymentType(order.getPaymentType());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setOrderValue(order.getOrderValue());
        orderDTO.setDiscount(order.getDiscount());
        orderDTO.setDate(order.getDate());
        orderDTO.setOrderNumber(order.getOrderNumber());
        orderDTO.setProducts(order.getProducts());

        this.gateway.update(orderDTO);
    }

    public abstract void updateStatus(Long orderNumber, Status status);
}
