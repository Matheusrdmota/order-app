package com.example.demo.order.usecases.interactors;

import com.example.demo.order.entities.Status;

public interface UpdateOrderStatusInteractor {
    void updateStatus(Long orderNumber, Status status);
}
