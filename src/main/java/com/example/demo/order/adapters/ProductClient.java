package com.example.demo.order.adapters;

import com.example.demo.product.usecases.dto.ResponseProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "product", url = "http://localhost:8080/api/v1")
public interface ProductClient {
    @RequestMapping(method = RequestMethod.GET, value = "/product/{id}")
    ResponseProductDTO getProductById(@PathVariable Long id);
}
