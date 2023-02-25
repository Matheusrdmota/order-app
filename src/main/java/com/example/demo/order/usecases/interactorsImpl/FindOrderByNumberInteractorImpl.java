package com.example.demo.order.usecases.interactorsImpl;

import com.example.demo.order.usecases.dto.ResponseOrderDTO;
import com.example.demo.order.usecases.gateways.OrderGateway;
import com.example.demo.order.usecases.interactors.FindOrderByNumberInteractor;

public class FindOrderByNumberInteractorImpl implements FindOrderByNumberInteractor {
    private OrderGateway gateway;

    public FindOrderByNumberInteractorImpl(OrderGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public ResponseOrderDTO findOrder(Long orderNumber) {
        ResponseOrderDTO responseOrderDTO = this.gateway.findOrderByNumber(orderNumber);

        if(responseOrderDTO == null){
            throw new RuntimeException("Pedido nao encontrado!");
        }

        return responseOrderDTO;
    }
}
