package com.example.demo.product.usecases;

import com.example.demo.product.entities.ProductCategoryEnum;
import com.example.demo.product.entities.ProductFactory;
import com.example.demo.product.usecases.dto.RequestProductDTO;
import com.example.demo.product.usecases.dto.ResponseProductDTO;
import com.example.demo.product.usecases.gateways.ProductGateway;
import com.example.demo.product.usecases.interactorsImpl.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class ProductUseCaseTest {
    static ProductGateway gateway;

    @BeforeAll
    static void setMock(){
        gateway = Mockito.mock(ProductGateway.class);
    }

    @Test
    void shouldRegisterIfDataIsValidAndIfTheProductDoesntExists(){
        ProductFactory factory = new ProductFactory();

        RequestProductDTO product = new RequestProductDTO(null, 1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        ResponseProductDTO response = new ResponseProductDTO(1L, 1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        when(gateway.findById(1L)).thenReturn(Optional.empty());
        when(gateway.save(product)).thenReturn(response);

        RegisterProductInteractorImpl registerProductInteractor = new RegisterProductInteractorImpl(gateway, factory);

        Assertions.assertEquals(registerProductInteractor.create(product), response);
    }

    @Test
    void shouldNotRegisterIfDataIsInvalid(){
        ProductFactory factory = new ProductFactory();

        RequestProductDTO product = new RequestProductDTO(null, 1L, -99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        when(gateway.findById(1L)).thenReturn(Optional.empty());

        RegisterProductInteractorImpl registerProductInteractor = new RegisterProductInteractorImpl(gateway, factory);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class,
                () -> registerProductInteractor.create(product),
                "Argumentos inválidos!");

        Assertions.assertEquals("Argumentos inválidos!", thrown.getMessage());
    }

    @Test
    void shouldNotRegisterIfProductAlreadyExists(){
        ProductFactory factory = new ProductFactory();

        RequestProductDTO product = new RequestProductDTO(null, 1L, -99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        ResponseProductDTO response = new ResponseProductDTO(1L, 1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        when(gateway.findById(1L)).thenReturn(Optional.of(response));

        RegisterProductInteractorImpl registerProductInteractor = new RegisterProductInteractorImpl(gateway, factory);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class,
                () -> registerProductInteractor.create(product),
                "Produto já existente!");

        Assertions.assertEquals("Produto já existente!", thrown.getMessage());
    }

    @Test
    void shouldFindProductIfExists(){
        ProductFactory factory = new ProductFactory();

        RequestProductDTO product = new RequestProductDTO(null, 1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        ResponseProductDTO response = new ResponseProductDTO(1L, 1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        when(gateway.findById(1L)).thenReturn(Optional.of(response));

        FindProductInteractorImpl findProductInteractor = new FindProductInteractorImpl(gateway);

        Assertions.assertEquals(response, findProductInteractor.findProductById(1L));
    }

    @Test
    void shouldNotFindProductIfDoesntExists(){
        ProductFactory factory = new ProductFactory();

        RequestProductDTO product = new RequestProductDTO(null, 1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        when(gateway.findById(1L)).thenReturn(Optional.empty());

        FindProductInteractorImpl findProductInteractor = new FindProductInteractorImpl(gateway);

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class,
                () -> findProductInteractor.findProductById(product.getBarCode()),
                "Produto com id " + product.getBarCode() + " não encontrado");

        Assertions.assertEquals("Produto com id 1 não encontrado", thrown.getMessage());
    }

    @Test
    void shouldFindAllProducts(){
        ProductFactory factory = new ProductFactory();

        ResponseProductDTO product1 = new ResponseProductDTO(1L, 1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        ResponseProductDTO product2 = new ResponseProductDTO(2L, 2L, 99.9,
                ProductCategoryEnum.ELETRODOMESTICO, "liquidi",
                "liquidi", "www.img.com", 40L);

        List<ResponseProductDTO> productArray = new ArrayList<>();
        productArray.add(product1);
        productArray.add(product2);

        when(gateway.findAll()).thenReturn(productArray);

        FindAllProductInteractorImpl findAllProductInteractor = new FindAllProductInteractorImpl(gateway);

        Assertions.assertEquals(productArray, findAllProductInteractor.findAll());
    }

    @Test
    void shouldDeleteProductIfExists(){
        ResponseProductDTO product = new ResponseProductDTO(1L, 1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        when(gateway.findById(1L)).thenReturn(Optional.of(product));

        DeleteProductInteractorImpl deleteProductInteractor = new DeleteProductInteractorImpl(gateway);

        Assertions.assertDoesNotThrow(() -> deleteProductInteractor.delete(1L));
    }

    @Test
    void shouldNotDeleteProductIfDoesntExists(){
        ResponseProductDTO product = new ResponseProductDTO(1L, 1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        when(gateway.findById(1L)).thenReturn(Optional.empty());

        DeleteProductInteractorImpl deleteProductInteractor = new DeleteProductInteractorImpl(gateway);

        Assertions.assertThrows(IllegalArgumentException.class, () -> deleteProductInteractor.delete(1L),
                "Produto não existe na base de dados!");
    }

    @Test
    void shouldUpdateProductIfExistsAndDataIsValid(){
        ProductFactory factory = new ProductFactory();

        ResponseProductDTO product = new ResponseProductDTO(1L, 1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        RequestProductDTO productUpdated = new RequestProductDTO(1L, 1L, 109.9,
                ProductCategoryEnum.MOVEL, "geladeira",
                "liquidi", "www.img.com", 40L);

        ResponseProductDTO responseUpdated = new ResponseProductDTO(1L, 1L, 109.9,
                ProductCategoryEnum.MOVEL, "geladeira",
                "liquidi", "www.img.com", 40L);

        when(gateway.findById(1L)).thenReturn(Optional.of(product));
        when(gateway.save(productUpdated)).thenReturn(responseUpdated);

        UpdateProductInteractorImpl updateProductInteractor = new UpdateProductInteractorImpl(gateway, factory);

        Assertions.assertEquals(responseUpdated, updateProductInteractor.update(productUpdated));
    }

    @Test
    void shouldNotUpdateProductIfDoesntExists(){
        ProductFactory factory = new ProductFactory();

        RequestProductDTO productUpdated = new RequestProductDTO(1L, 1L, 109.9,
                ProductCategoryEnum.MOVEL, "geladeira",
                "liquidi", "www.img.com", 40L);

        when(gateway.findById(productUpdated.getId())).thenReturn(Optional.empty());

        UpdateProductInteractorImpl updateProductInteractor = new UpdateProductInteractorImpl(gateway, factory);

        Assertions.assertThrows(IllegalArgumentException.class, () -> updateProductInteractor.update(productUpdated),
                "Produto não existe na base de dados!");
    }

    @Test
    void shouldNotUpdateProductIfDataIsInvalid(){
        ProductFactory factory = new ProductFactory();

        ResponseProductDTO product = new ResponseProductDTO(1L, 1L, 99.9,
                ProductCategoryEnum.MOVEL, "liquidi",
                "liquidi", "www.img.com", 40L);

        RequestProductDTO productUpdated = new RequestProductDTO(1L, 1L, -109.9,
                ProductCategoryEnum.MOVEL, "geladeira",
                "liquidi", "www.img.com", 40L);

        when(gateway.findById(productUpdated.getId())).thenReturn(Optional.of(product));

        UpdateProductInteractorImpl updateProductInteractor = new UpdateProductInteractorImpl(gateway, factory);

        Assertions.assertThrows(IllegalArgumentException.class, () -> updateProductInteractor.update(productUpdated),
                "Argumentos inválidos!");
    }

}
