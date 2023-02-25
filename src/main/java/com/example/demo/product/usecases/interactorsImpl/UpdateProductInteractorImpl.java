package com.example.demo.product.usecases.interactorsImpl;

import com.example.demo.product.entities.Product;
import com.example.demo.product.entities.ProductFactory;
import com.example.demo.product.usecases.dto.RequestProductDTO;
import com.example.demo.product.usecases.dto.ResponseProductDTO;
import com.example.demo.product.usecases.gateways.ProductGateway;
import com.example.demo.product.usecases.interactors.UpdateProductInteractor;

import java.util.Optional;

public class UpdateProductInteractorImpl implements UpdateProductInteractor {
    private ProductGateway gateway;
    private ProductFactory factory;

    public UpdateProductInteractorImpl(ProductGateway gateway, ProductFactory factory){
        this.gateway = gateway;
        this.factory = factory;
    }

    @Override
    public ResponseProductDTO update(RequestProductDTO requestProductDTO) {
        Optional<ResponseProductDTO> productToBeUpdated = this.gateway.findById(requestProductDTO.getBarCode());
        if(!productToBeUpdated.isPresent()){
            throw new IllegalArgumentException("Produto não existe na base de dados!");
        }
        Product commonProduct = this.factory.createProduct(requestProductDTO.getBarCode(), requestProductDTO.getPrice(), requestProductDTO.getCategory(),
                requestProductDTO.getProductName(), requestProductDTO.getDescription(), requestProductDTO.getUrlImage(), requestProductDTO.getStockQuantity());

        if(!commonProduct.priceAndBarCodeIsValid()){
            throw new IllegalArgumentException("Argumentos inválidos!");
        }

        requestProductDTO.setId(productToBeUpdated.get().getId());

        return this.gateway.save(requestProductDTO);
    }
}
