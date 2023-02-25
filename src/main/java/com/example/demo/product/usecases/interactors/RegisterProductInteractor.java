package com.example.demo.product.usecases.interactors;

import com.example.demo.product.usecases.dto.RequestProductDTO;
import com.example.demo.product.usecases.dto.ResponseProductDTO;

public interface RegisterProductInteractor {
    ResponseProductDTO create(RequestProductDTO product);
}
