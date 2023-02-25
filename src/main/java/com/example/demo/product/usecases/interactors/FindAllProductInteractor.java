package com.example.demo.product.usecases.interactors;

import com.example.demo.product.usecases.dto.ResponseProductDTO;

import java.util.List;

public interface FindAllProductInteractor {
    List<ResponseProductDTO> findAll();
}
