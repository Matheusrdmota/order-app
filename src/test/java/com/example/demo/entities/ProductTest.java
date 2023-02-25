package com.example.demo.entities;

import com.example.demo.product.entities.Product;
import com.example.demo.product.entities.ProductCategoryEnum;
import com.example.demo.product.entities.ProductFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

public class ProductTest {
    private Product product;
    private ProductFactory factory;

    @Test
    public void shouldCreateIfDataIsValid(){
        factory = new ProductFactory();
        product = factory.createProduct(1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        Assertions.assertTrue(product.priceAndBarCodeIsValid());
    }

    @Test
    public void ifStockIsGreaterThanZeroThenShouldReturnTrue(){
        factory = new ProductFactory();
        product = factory.createProduct(1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        Assertions.assertTrue(product.verifyIfHasStock());
    }

    @Test
    public void shouldNotCreateIfPriceIsNegative(){
        factory = new ProductFactory();
        product = factory.createProduct(1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        Assertions.assertFalse(product.priceAndBarCodeIsValid());
    }

    @Test
    public void shouldNotCreateIfStockIsNegative(){
        factory = new ProductFactory();
        product = factory.createProduct(1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", -40L);

        Assertions.assertFalse(product.priceAndBarCodeIsValid());
    }

    @Test
    public void shouldNotCreateIfBarcodeIsNegative(){
        factory = new ProductFactory();
        product = factory.createProduct(-1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        Assertions.assertFalse(product.priceAndBarCodeIsValid());
    }
}
