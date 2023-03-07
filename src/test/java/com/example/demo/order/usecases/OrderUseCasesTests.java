package com.example.demo.order.usecases;

import com.example.demo.order.entities.PaymentType;
import com.example.demo.order.entities.Product;
import com.example.demo.order.entities.Status;
import com.example.demo.order.usecases.dto.RequestOrderDTO;
import com.example.demo.order.usecases.dto.RequestOrderProductDTO;
import com.example.demo.order.usecases.dto.ResponseOrderDTO;
import com.example.demo.order.usecases.gateways.OrderGateway;
import com.example.demo.order.usecases.interactorsImpl.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class OrderUseCasesTests {
    static OrderGateway gateway;

    @BeforeAll
    static void setGatewayMock(){ gateway = Mockito.mock(OrderGateway.class); }

    @Test
    public void shouldCreateOrderIfDoesntExistsAndDataIsValid(){
        RequestOrderDTO request = new RequestOrderDTO();
        request.setOrderNumber(1L);
        request.setClient("Rebeca");
        request.setPaymentType(PaymentType.CARTAO);
        request.setDiscount(BigDecimal.ZERO);

        ResponseOrderDTO response = new ResponseOrderDTO();
        response.setOrderNumber(1L);
        response.setClient("Rebeca");
        response.setPaymentType(PaymentType.CARTAO);
        response.setDiscount(BigDecimal.ZERO);
        response.setDate(LocalDateTime.now());
        response.setOrderValue(BigDecimal.ZERO);
        response.setProducts(new ArrayList<>());
        response.setStatus(Status.PENDENTE);

        when(gateway.findOrderByNumber(1L)).thenReturn(null);
        when(gateway.create(request)).thenReturn(response);

        CreateOrderInteractorImpl interactor = new CreateOrderInteractorImpl(gateway);

        assertEquals(interactor.createOrder(request), response);
    }

    @Test
    public void shouldNotCreateOrderIfExists(){
        RequestOrderDTO request = new RequestOrderDTO();
        request.setOrderNumber(1L);
        request.setClient("Rebeca");
        request.setPaymentType(PaymentType.CARTAO);
        request.setDiscount(BigDecimal.ZERO);

        ResponseOrderDTO response = new ResponseOrderDTO();
        response.setOrderNumber(1L);
        response.setClient("Rebeca");
        response.setPaymentType(PaymentType.CARTAO);
        response.setDiscount(BigDecimal.ZERO);
        response.setDate(LocalDateTime.now());
        response.setOrderValue(BigDecimal.ZERO);
        response.setProducts(new ArrayList<>());
        response.setStatus(Status.PENDENTE);

        when(gateway.findOrderByNumber(1L)).thenReturn(response);

        CreateOrderInteractorImpl interactor = new CreateOrderInteractorImpl(gateway);

        assertThrows(IllegalArgumentException.class, () -> interactor.createOrder(request), "Numero de pedido ja existe!");
    }

    @Test
    public void shouldNotCreateOrderIfDiscountIsInvalid(){
        RequestOrderDTO request = new RequestOrderDTO();
        request.setOrderNumber(1L);
        request.setClient("Rebeca");
        request.setPaymentType(PaymentType.CARTAO);
        request.setDiscount(BigDecimal.valueOf(0.25));

        when(gateway.findOrderByNumber(1L)).thenReturn(null);

        CreateOrderInteractorImpl interactor = new CreateOrderInteractorImpl(gateway);

        assertThrows(IllegalArgumentException.class, () -> interactor.createOrder(request), "porcentagem de desconto inválida!");
    }

    @Test
    public void shouldFindOrderIfExists(){
        RequestOrderDTO request = new RequestOrderDTO();
        request.setOrderNumber(1L);
        request.setClient("Rebeca");
        request.setPaymentType(PaymentType.CARTAO);
        request.setDiscount(BigDecimal.ZERO);

        ResponseOrderDTO response = new ResponseOrderDTO();
        response.setOrderNumber(1L);
        response.setClient("Rebeca");
        response.setPaymentType(PaymentType.CARTAO);
        response.setDiscount(BigDecimal.ZERO);
        response.setDate(LocalDateTime.now());
        response.setOrderValue(BigDecimal.ZERO);
        response.setProducts(new ArrayList<>());
        response.setStatus(Status.PENDENTE);

        when(gateway.findOrderByNumber(1L)).thenReturn(response);

        FindOrderByNumberInteractorImpl interactor = new FindOrderByNumberInteractorImpl(gateway);

        assertEquals(interactor.findOrder(1L), response);
    }

    @Test
    public void shouldNotFindOrderIfDoesntExists(){
        RequestOrderDTO request = new RequestOrderDTO();
        request.setOrderNumber(1L);
        request.setClient("Rebeca");
        request.setPaymentType(PaymentType.CARTAO);
        request.setDiscount(BigDecimal.ZERO);

        when(gateway.findOrderByNumber(1L)).thenReturn(null);

        FindOrderByNumberInteractorImpl interactor = new FindOrderByNumberInteractorImpl(gateway);

        assertThrows(RuntimeException.class, () -> interactor.findOrder(1L), "Pedido nao encontrado!");
    }

    @Test
    public void shouldAddProductIfHasStock(){
        ResponseOrderDTO response = new ResponseOrderDTO();
        response.setOrderNumber(1L);
        response.setClient("Rebeca");
        response.setPaymentType(PaymentType.CARTAO);
        response.setDiscount(BigDecimal.ZERO);
        response.setDate(LocalDateTime.now());
        response.setOrderValue(BigDecimal.ZERO);
        response.setProducts(new ArrayList<>());
        response.setStatus(Status.PENDENTE);

        RequestOrderProductDTO productDTO = new RequestOrderProductDTO();
        productDTO.setBarCode(1L);
        productDTO.setProductName("Liquidificador");
        productDTO.setPrice(100.00);
        productDTO.setOrderNumber(1L);
        productDTO.setQuantity(2L);
        productDTO.setStockQuantity(10L);

        Product product = new Product();

        product.setOrderNumber(productDTO.getOrderNumber());
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setBarCode(productDTO.getBarCode());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setQuantity(productDTO.getQuantity());

        when(gateway.findOrderByNumber(1L)).thenReturn(response);

        AddProductInteractorImpl interactor = new AddProductInteractorImpl(gateway);

        response.getProducts().add(product);
        response.setOrderValue(BigDecimal.valueOf(100.00));

        assertEquals(interactor.addProduct(1L, productDTO), response);
    }

    @Test
    public void shouldNotAddProductIfHasntStock(){
        ResponseOrderDTO response = new ResponseOrderDTO();
        response.setOrderNumber(1L);
        response.setClient("Rebeca");
        response.setPaymentType(PaymentType.CARTAO);
        response.setDiscount(BigDecimal.ZERO);
        response.setDate(LocalDateTime.now());
        response.setOrderValue(BigDecimal.ZERO);
        response.setProducts(new ArrayList<>());
        response.setStatus(Status.PENDENTE);

        RequestOrderProductDTO productDTO = new RequestOrderProductDTO();
        productDTO.setBarCode(1L);
        productDTO.setProductName("Liquidificador");
        productDTO.setPrice(100.00);
        productDTO.setOrderNumber(1L);
        productDTO.setQuantity(20L);
        productDTO.setStockQuantity(10L);

        when(gateway.findOrderByNumber(1L)).thenReturn(response);

        AddProductInteractorImpl interactor = new AddProductInteractorImpl(gateway);

        assertThrows(RuntimeException.class, () -> interactor.addProduct(1L, productDTO), "Produto não possui estoque suficiente!");
    }

    @Test
    public void shouldSetStatusToAwaitingForPayment(){
        ResponseOrderDTO response = new ResponseOrderDTO();
        response.setOrderNumber(1L);
        response.setClient("Rebeca");
        response.setPaymentType(PaymentType.CARTAO);
        response.setDiscount(BigDecimal.ZERO);
        response.setDate(LocalDateTime.now());
        response.setOrderValue(BigDecimal.ZERO);
        response.setProducts(new ArrayList<>());
        response.setStatus(Status.PENDENTE);

        when(gateway.findOrderByNumber(1L)).thenReturn(response);

        AwaitForPaymentInteractorImpl interactor = new AwaitForPaymentInteractorImpl(gateway);

        assertDoesNotThrow(() -> interactor.updateStatus(1L, Status.AGUARDANDO_PAGAMENTO));
    }

    @Test
    public void shouldThrowErrorIfOrderDoesntExistsWhileUpdatingStatusToAwaitingForPayment(){
        ResponseOrderDTO response = new ResponseOrderDTO();
        response.setOrderNumber(1L);
        response.setClient("Rebeca");
        response.setPaymentType(PaymentType.CARTAO);
        response.setDiscount(BigDecimal.ZERO);
        response.setDate(LocalDateTime.now());
        response.setOrderValue(BigDecimal.ZERO);
        response.setProducts(new ArrayList<>());
        response.setStatus(Status.PENDENTE);

        when(gateway.findOrderByNumber(1L)).thenReturn(null);

        AwaitForPaymentInteractorImpl interactor = new AwaitForPaymentInteractorImpl(gateway);

        assertThrows(RuntimeException.class, () -> interactor.updateStatus(1L, Status.AGUARDANDO_PAGAMENTO), "Pedido nao encontrado!");
    }

    @Test
    public void shouldSetStatusToCanceled(){
        ResponseOrderDTO response = new ResponseOrderDTO();
        response.setOrderNumber(1L);
        response.setClient("Rebeca");
        response.setPaymentType(PaymentType.CARTAO);
        response.setDiscount(BigDecimal.ZERO);
        response.setDate(LocalDateTime.now());
        response.setOrderValue(BigDecimal.ZERO);
        response.setProducts(new ArrayList<>());
        response.setStatus(Status.PENDENTE);

        when(gateway.findOrderByNumber(1L)).thenReturn(response);

        CancelOrderInteractorImpl interactor = new CancelOrderInteractorImpl(gateway);

        assertDoesNotThrow(() -> interactor.updateStatus(1L, Status.CANCELADO));
    }

    @Test
    public void shouldThrowErrorIfOrderDoesntExistsWhileUpdatingStatusToCanceled(){
        ResponseOrderDTO response = new ResponseOrderDTO();
        response.setOrderNumber(1L);
        response.setClient("Rebeca");
        response.setPaymentType(PaymentType.CARTAO);
        response.setDiscount(BigDecimal.ZERO);
        response.setDate(LocalDateTime.now());
        response.setOrderValue(BigDecimal.ZERO);
        response.setProducts(new ArrayList<>());
        response.setStatus(Status.PENDENTE);

        when(gateway.findOrderByNumber(1L)).thenReturn(null);

        CancelOrderInteractorImpl interactor = new CancelOrderInteractorImpl(gateway);

        assertThrows(RuntimeException.class, () -> interactor.updateStatus(1L, Status.CANCELADO), "Pedido nao encontrado!");
    }


    @Test
    public void shouldSetStatusToFinished(){
        ResponseOrderDTO response = new ResponseOrderDTO();
        response.setOrderNumber(1L);
        response.setClient("Rebeca");
        response.setPaymentType(PaymentType.CARTAO);
        response.setDiscount(BigDecimal.ZERO);
        response.setDate(LocalDateTime.now());
        response.setOrderValue(BigDecimal.ZERO);
        response.setProducts(new ArrayList<>());
        response.setStatus(Status.AGUARDANDO_PAGAMENTO);

        when(gateway.findOrderByNumber(1L)).thenReturn(response);

        FinishOrderInteractorImpl interactor = new FinishOrderInteractorImpl(gateway);

        assertDoesNotThrow(() -> interactor.updateStatus(1L, Status.CONCLUIDO));
    }

    @Test
    public void shouldThrowErrorIfOrderDoesntExistsWhileUpdatingStatusToFinished(){
        ResponseOrderDTO response = new ResponseOrderDTO();
        response.setOrderNumber(1L);
        response.setClient("Rebeca");
        response.setPaymentType(PaymentType.CARTAO);
        response.setDiscount(BigDecimal.ZERO);
        response.setDate(LocalDateTime.now());
        response.setOrderValue(BigDecimal.ZERO);
        response.setProducts(new ArrayList<>());
        response.setStatus(Status.AGUARDANDO_PAGAMENTO);

        when(gateway.findOrderByNumber(1L)).thenReturn(null);

        FinishOrderInteractorImpl interactor = new FinishOrderInteractorImpl(gateway);

        assertThrows(RuntimeException.class, () -> interactor.updateStatus(1L, Status.CONCLUIDO), "Pedido nao encontrado!");
    }

}
