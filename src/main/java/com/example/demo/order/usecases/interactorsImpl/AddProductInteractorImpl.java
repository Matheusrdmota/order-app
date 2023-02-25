package com.example.demo.order.usecases.interactorsImpl;

import com.example.demo.order.entities.Product;
import com.example.demo.order.usecases.dto.RequestOrderProductDTO;
import com.example.demo.order.usecases.dto.ResponseOrderDTO;
import com.example.demo.order.usecases.gateways.OrderGateway;
import com.example.demo.order.usecases.interactors.AddProductInteractor;

import java.math.BigDecimal;

public class AddProductInteractorImpl implements AddProductInteractor {
    private OrderGateway gateway;

    public AddProductInteractorImpl(OrderGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public ResponseOrderDTO addProduct(Long orderNumber, RequestOrderProductDTO productDTO) {
        ResponseOrderDTO responseOrderDTO = this.gateway.findOrderByNumber(orderNumber);
        Product product = new Product();

        product.setOrderNumber(productDTO.getOrderNumber());
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setBarCode(productDTO.getBarCode());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setQuantity(productDTO.getQuantity());

        if(!product.productHasStock()){
            throw new RuntimeException("Produto não possui estoque suficiente!");
        }

        responseOrderDTO.getProducts().add(product);
        BigDecimal value = BigDecimal.valueOf(product.getPrice() * product.getQuantity()).multiply(BigDecimal.ONE.subtract(responseOrderDTO.getDiscount()));

        responseOrderDTO.setOrderValue(responseOrderDTO.getOrderValue().add(value));

        this.gateway.update(responseOrderDTO);

        return responseOrderDTO;
    }
}
