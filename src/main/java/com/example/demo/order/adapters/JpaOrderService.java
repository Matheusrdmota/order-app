package com.example.demo.order.adapters;

import com.example.demo.order.entities.Product;
import com.example.demo.order.usecases.dto.RequestOrderDTO;
import com.example.demo.order.usecases.dto.ResponseOrderDTO;
import com.example.demo.order.usecases.gateways.OrderGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class JpaOrderService implements OrderGateway {
    @Autowired
    private JpaOrderRepository repository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseOrderDTO create(RequestOrderDTO requestOrderDTO) {
        OrderDataMapper orderDataMapper = this.objectMapper.convertValue(requestOrderDTO, OrderDataMapper.class);

        ResponseOrderDTO response = this.objectMapper.convertValue(this.repository.save(orderDataMapper), ResponseOrderDTO.class);

        return response;
    }

    @Override
    public ResponseOrderDTO findOrderByNumber(Long orderNumber) {
        OrderDataMapper orderMapper = this.repository.findByOrderNumber(orderNumber);

        ResponseOrderDTO response = new ResponseOrderDTO();

        if(orderMapper == null){
            return null;
        }

        response.setOrderNumber(orderMapper.getOrderNumber());
        response.setOrderValue(orderMapper.getOrderValue());
        response.setId(orderMapper.getId());
        response.setClient(orderMapper.getClient());
        response.setDate(orderMapper.getDate());
        response.setDiscount(orderMapper.getDiscount());
        response.setPaymentType(orderMapper.getPaymentType());
        response.setStatus(orderMapper.getStatus());
        response.setProducts(orderMapper.getProducts().stream().map(x -> {
            Product product = new Product();
            try {
                product = this.objectMapper.readValue(x, Product.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return product;
        }).collect(Collectors.toList()));

        return response;
    }

    @Override
    public ResponseOrderDTO update(ResponseOrderDTO orderDTO){
        OrderDataMapper orderMapper = this.repository.findByOrderNumber(orderDTO.getOrderNumber());

        orderMapper.setOrderNumber(orderDTO.getOrderNumber());
        orderMapper.setOrderValue(orderDTO.getOrderValue());
        orderMapper.setId(orderDTO.getId());
        orderMapper.setClient(orderDTO.getClient());
        orderMapper.setDate(orderDTO.getDate());
        orderMapper.setDiscount(orderDTO.getDiscount());
        orderMapper.setPaymentType(orderDTO.getPaymentType());
        orderMapper.setStatus(orderDTO.getStatus());
        orderMapper.setProducts(orderDTO.getProducts().stream().map(x -> {
            String str = "";
            try {
                str = this.objectMapper.writeValueAsString(x);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return str;
        }).collect(Collectors.toList()));

        this.repository.save(orderMapper);

        return orderDTO;
    }
}
