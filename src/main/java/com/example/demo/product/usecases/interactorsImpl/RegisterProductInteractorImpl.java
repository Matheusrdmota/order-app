package com.example.demo.product.usecases.interactorsImpl;

import com.example.demo.product.entities.Product;
import com.example.demo.product.entities.ProductFactory;
import com.example.demo.product.usecases.dto.RequestProductDTO;
import com.example.demo.product.usecases.dto.ResponseProductDTO;
import com.example.demo.product.usecases.gateways.ProductGateway;
import com.example.demo.product.usecases.interactors.RegisterProductInteractor;

public class RegisterProductInteractorImpl implements RegisterProductInteractor {
    private final ProductGateway productRepository;
    private final ProductFactory factory;

    public RegisterProductInteractorImpl(ProductGateway productRepository,
                                         ProductFactory factory){
        this.productRepository = productRepository;
        this.factory = factory;
    }

    @Override
    public ResponseProductDTO create(RequestProductDTO product) {
        if(this.productRepository.findById(product.getBarCode()).isPresent()){
            throw new IllegalArgumentException("Produto já existente!");
        }

        Product commonProduct = this.factory.createProduct(product.getBarCode(), product.getPrice(), product.getCategory(),
                product.getProductName(), product.getDescription(), product.getUrlImage(), product.getStockQuantity());

        if(!commonProduct.priceAndBarCodeIsValid()){
            throw new IllegalArgumentException("Argumentos inválidos!");
        }

        return this.productRepository.save(product);
    }
}
