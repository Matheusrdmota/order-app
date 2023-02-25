package com.example.demo.product.usecases.gateways;

import com.example.demo.product.usecases.dto.RequestProductDTO;
import com.example.demo.product.usecases.dto.ResponseProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {
    Optional<ResponseProductDTO> findById(Long id);
    ResponseProductDTO save(RequestProductDTO productDTO);
    List<ResponseProductDTO> findAll();
    void delete(Long id);
}
