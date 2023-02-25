package com.example.demo.order.usecases.interactors;

import com.example.demo.order.usecases.dto.RequestOrderProductDTO;
import com.example.demo.order.usecases.dto.ResponseOrderDTO;

public interface AddProductInteractor {
    ResponseOrderDTO addProduct(Long orderNumber, RequestOrderProductDTO productDTO);
}
