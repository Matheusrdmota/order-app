package com.example.demo.product.adapters;

import com.example.demo.product.usecases.gateways.ProductGateway;
import com.example.demo.product.usecases.dto.RequestProductDTO;
import com.example.demo.product.usecases.dto.ResponseProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaProduct implements ProductGateway {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public Optional<ResponseProductDTO> findById(Long id) {
        Optional<ProductDataMapper> response = this.repository.findByBarCode(id);
        if(response.isPresent()){
            return Optional.of(mapper.convertValue(response, ResponseProductDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public ResponseProductDTO save(RequestProductDTO productDTO) {
        ProductDataMapper productDataMapper = mapper.convertValue(productDTO, ProductDataMapper.class);

        this.repository.save(productDataMapper);

        ResponseProductDTO response = mapper.convertValue(productDTO, ResponseProductDTO.class);

        return response;
    }

    @Override
    public List<ResponseProductDTO> findAll() {
        List<ProductDataMapper> response = this.repository.findAll();

        List<ResponseProductDTO> responseProductDTOList = response.stream()
                                                            .map(product -> mapper.convertValue(product, ResponseProductDTO.class))
                                                            .collect(Collectors.toList());

        return responseProductDTOList;
    }

    @Override
    public void delete(Long id) {
        ProductDataMapper productDataMapper = mapper.convertValue(this.findById(id).get(), ProductDataMapper.class);

        this.repository.delete(productDataMapper);
    }

}
