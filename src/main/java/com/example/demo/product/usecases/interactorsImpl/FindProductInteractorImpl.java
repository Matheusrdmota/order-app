package com.example.demo.product.usecases.interactorsImpl;

import com.example.demo.product.usecases.dto.ResponseProductDTO;
import com.example.demo.product.usecases.gateways.ProductGateway;
import com.example.demo.product.usecases.interactors.FindProductInteractor;

import java.util.Optional;

public class FindProductInteractorImpl implements FindProductInteractor {
    private ProductGateway gateway;

    public FindProductInteractorImpl(ProductGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public ResponseProductDTO findProductById(Long id) {
        if(id < 0){
            throw new IllegalArgumentException("Id inválido");
        }

        Optional<ResponseProductDTO> response = this.gateway.findById(id);
        if(!response.isPresent()){
            throw new RuntimeException("Produto com id " + id + " não encontrado");
        }

        return response.get();
    }
}
