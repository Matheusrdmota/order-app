package com.example.demo.usecases;

import com.example.demo.product.entities.ProductCategoryEnum;
import com.example.demo.product.entities.ProductFactory;
import com.example.demo.product.usecases.dto.RequestProductDTO;
import com.example.demo.product.usecases.dto.ResponseProductDTO;
import com.example.demo.product.usecases.gateways.ProductGateway;
import com.example.demo.product.usecases.interactorsImpl.RegisterProductInteractorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
}
