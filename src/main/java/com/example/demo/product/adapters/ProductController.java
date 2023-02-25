package com.example.demo.product.adapters;

import com.example.demo.product.usecases.interactors.*;
import com.example.demo.product.usecases.dto.RequestProductDTO;
import com.example.demo.product.usecases.dto.ResponseProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private RegisterProductInteractor productInteractor;
    @Autowired
    private FindProductInteractor findProductInteractor;
    @Autowired
    private FindAllProductInteractor findAllProductInteractor;
    @Autowired
    private UpdateProductInteractor updateProductInteractor;
    @Autowired
    private DeleteProductInteractor deleteProductInteractor;

    @PostMapping
    public ResponseEntity<ResponseProductDTO> create(@RequestBody RequestProductDTO productDTO){
        return ResponseEntity.ok(this.productInteractor.create(productDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.findProductInteractor.findProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> findAll(){
        return ResponseEntity.ok(this.findAllProductInteractor.findAll());
    }

    @PutMapping
    public ResponseEntity<ResponseProductDTO> updateProduct(@RequestBody RequestProductDTO productDTO){
        return ResponseEntity.ok(this.updateProductInteractor.update(productDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        this.deleteProductInteractor.delete(id);
    }
}