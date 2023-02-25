package com.example.demo.order.adapters;

import com.example.demo.order.entities.Status;
import com.example.demo.order.usecases.dto.RequestOrderDTO;
import com.example.demo.order.usecases.dto.ResponseOrderDTO;
import com.example.demo.order.usecases.dto.RequestOrderProductDTO;
import com.example.demo.order.usecases.interactors.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private CreateOrderInteractor createOrderInteractor;
    @Autowired
    private FindOrderByNumberInteractor findOrderByNumberInteractor;
    @Autowired
    private AddProductInteractor addProductInteractor;
    @Autowired
    private List<UpdateOrderStatusInteractor> updateOrderStatusInteractor;
    @Autowired
    private ProductClient client;
    @Autowired
    private ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseOrderDTO> create(@RequestBody RequestOrderDTO requestOrderDTO){
        return ResponseEntity.ok(this.createOrderInteractor.createOrder(requestOrderDTO));
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<ResponseOrderDTO> findById(@PathVariable Long orderNumber){
        return ResponseEntity.ok(this.findOrderByNumberInteractor.findOrder(orderNumber));
    }

    @PatchMapping("/{orderNumber}")
    public ResponseEntity<ResponseOrderDTO> addProduct(@PathVariable Long orderNumber, @RequestBody AddProductDTO productDto){
        RequestOrderProductDTO dto = this.mapper.convertValue(this.client.getProductById(productDto.getBarCode()), RequestOrderProductDTO.class);
        dto.setQuantity(productDto.getQuantity());
        return ResponseEntity.ok(this.addProductInteractor.addProduct(orderNumber, dto));
    }

    @PatchMapping("status/{orderNumber}/{status}")
    public ResponseEntity finishOrder(@PathVariable Long orderNumber, @PathVariable Status status){
        //OBSERVER
        this.updateOrderStatusInteractor.forEach(x -> x.updateStatus(orderNumber, status));
        return ResponseEntity.ok().build();
    }

}
