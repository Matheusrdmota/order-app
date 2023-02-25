package com.example.demo.order.usecases.gateways;

import com.example.demo.order.usecases.dto.RequestOrderDTO;
import com.example.demo.order.usecases.dto.ResponseOrderDTO;

public interface OrderGateway {
    ResponseOrderDTO create(RequestOrderDTO requestOrderDTO);
    ResponseOrderDTO findOrderByNumber(Long orderNumber);
    ResponseOrderDTO update(ResponseOrderDTO orderDTO);
}
