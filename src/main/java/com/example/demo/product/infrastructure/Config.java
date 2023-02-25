package com.example.demo.product.infrastructure;

import com.example.demo.product.adapters.JpaProduct;
import com.example.demo.product.entities.ProductFactory;
import com.example.demo.product.usecases.interactorsImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Autowired
    private JpaProduct productRepository;

    @Bean
    public ProductFactory productFactory(){
        return new ProductFactory();
    }

    @Bean
    public RegisterProductInteractorImpl registerProductInteractorImpl(){
        return new RegisterProductInteractorImpl(productRepository, productFactory());
    }

    @Bean
    public FindProductInteractorImpl findProductInteractorImpl(){
        return new FindProductInteractorImpl(productRepository);
    }

    @Bean
    public FindAllProductInteractorImpl findAllProductInteractor(){
        return new FindAllProductInteractorImpl(productRepository);
    }

    @Bean
    public UpdateProductInteractorImpl updateProductInteractor(){
        return new UpdateProductInteractorImpl(productRepository, productFactory());
    }

    @Bean
    public DeleteProductInteractorImpl deleteProductInteractor(){
        return new DeleteProductInteractorImpl(productRepository);
    }

}
