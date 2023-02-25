package com.example.demo.order.usecases.interactors;

import com.example.demo.order.usecases.dto.ResponseOrderDTO;

public interface FindOrderByNumberInteractor {
    ResponseOrderDTO findOrder(Long orderNumber);
}
