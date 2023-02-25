package com.example.demo.product.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductDataMapper, Long> {
    Optional<ProductDataMapper> findByBarCode( Long barCode);
}
