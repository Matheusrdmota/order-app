package com.example.demo.product.usecases.interactorsImpl;

import com.example.demo.product.usecases.dto.ResponseProductDTO;
import com.example.demo.product.usecases.gateways.ProductGateway;
import com.example.demo.product.usecases.interactors.DeleteProductInteractor;

import java.util.Optional;

public class DeleteProductInteractorImpl implements DeleteProductInteractor {
    private ProductGateway gateway;

    public DeleteProductInteractorImpl(ProductGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public void delete(Long id) {
        Optional<ResponseProductDTO> productToBeUpdated = this.gateway.findById(id);
        if(!productToBeUpdated.isPresent()){
            throw new IllegalArgumentException("Produto não existe na base de dados!");
        }

        this.gateway.delete(id);
    }
}
