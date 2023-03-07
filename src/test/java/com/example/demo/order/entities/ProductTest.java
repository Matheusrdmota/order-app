package com.example.demo.order.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void shouldSucceedIfProductHasStock(){
        Product product = new Product();
        product.setStockQuantity(10L);
        product.setQuantity(2L);

        Assertions.assertTrue(product.productHasStock());
    }

    @Test
    public void shouldFailIfProductHasntStock(){
        Product product = new Product();
        product.setStockQuantity(10L);
        product.setQuantity(11L);

        Assertions.assertFalse(product.productHasStock());
    }
}
