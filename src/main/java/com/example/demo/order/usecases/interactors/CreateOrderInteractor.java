package com.example.demo.order.usecases.interactors;

import com.example.demo.order.usecases.dto.RequestOrderDTO;
import com.example.demo.order.usecases.dto.ResponseOrderDTO;

public interface CreateOrderInteractor {
    ResponseOrderDTO createOrder(RequestOrderDTO requestOrderDTO);
}
