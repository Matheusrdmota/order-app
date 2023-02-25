package com.example.demo.order.usecases.interactorsImpl;

import com.example.demo.order.entities.Order;
import com.example.demo.order.entities.Status;
import com.example.demo.order.usecases.dto.ResponseOrderDTO;
import com.example.demo.order.usecases.gateways.OrderGateway;
import com.example.demo.order.usecases.interactors.UpdateOrderStatusInteractor;

public class FinishOrderInteractorImpl implements UpdateOrderStatusInteractor {
    private OrderGateway gateway;

    public FinishOrderInteractorImpl(OrderGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public void updateStatus(Long orderNumber, Status status) {
        if(!status.equals(Status.CONCLUIDO)){
            return;
        }

        ResponseOrderDTO orderDTO = this.gateway.findOrderByNumber(orderNumber);

        if(orderDTO == null){
            throw new RuntimeException("Pedido nao encontrado!");
        }

        Order order = new Order();

        order.setClient(orderDTO.getClient());
        order.setPaymentType(orderDTO.getPaymentType());
        order.setStatus(orderDTO.getStatus());
        order.setOrderValue(orderDTO.getOrderValue());
        order.setDiscount(orderDTO.getDiscount());
        order.setDate(orderDTO.getDate());
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setProducts(orderDTO.getProducts());

        order.finish();

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
}
