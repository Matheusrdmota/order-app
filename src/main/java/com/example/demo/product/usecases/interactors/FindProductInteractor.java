package com.example.demo.product.usecases.interactors;

import com.example.demo.product.usecases.dto.ResponseProductDTO;

public interface FindProductInteractor {
    ResponseProductDTO findProductById(Long id);
}
