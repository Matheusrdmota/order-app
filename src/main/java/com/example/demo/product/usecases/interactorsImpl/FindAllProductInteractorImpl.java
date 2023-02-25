package com.example.demo.product.usecases.interactorsImpl;

import com.example.demo.product.usecases.dto.ResponseProductDTO;
import com.example.demo.product.usecases.gateways.ProductGateway;
import com.example.demo.product.usecases.interactors.FindAllProductInteractor;

import java.util.List;

public class FindAllProductInteractorImpl implements FindAllProductInteractor {
    private ProductGateway gateway;

    public FindAllProductInteractorImpl(ProductGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<ResponseProductDTO> findAll() {
        return this.gateway.findAll();
    }
}
