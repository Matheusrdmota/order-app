package com.example.demo.order.entities;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderTest {

    @Test
    public void shouldPassAllValidationsIfDataIsValid(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.CARTAO);
        order.setStatus(Status.PENDENTE);

        order.setDiscountIfPaymentTypeIsDinheiroOrPix();

        Assertions.assertTrue(order.isValidDiscount());
        Assertions.assertEquals(order.getDiscount(), BigDecimal.ZERO);
    }

    @Test
    public void shouldFailIfDiscountIsGreaterThanTwentyPercent(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.CARTAO);
        order.setStatus(Status.PENDENTE);
        order.setDiscount(BigDecimal.valueOf(0.25));

        Assertions.assertFalse(order.isValidDiscount());
    }

    @Test
    public void shouldSetDiscountToFifteenPercentIfPaymentTypeIsPixOrDinheiro(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.PENDENTE);

        order.setDiscountIfPaymentTypeIsDinheiroOrPix();

        Assertions.assertEquals(order.getDiscount(), BigDecimal.valueOf(0.15));

        order.setPaymentType(PaymentType.DINHEIRO);
        order.setDiscountIfPaymentTypeIsDinheiroOrPix();

        Assertions.assertEquals(order.getDiscount(), BigDecimal.valueOf(0.15));
    }

    @Test
    public void shouldFailStateTransitionIfItIsFromPendingToFinished(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.PENDENTE);

        Assertions.assertThrows(IllegalStateException.class, () -> order.finish(), "Transição de PENDENTE para CONCLUIDO é inválida!");
    }

    @Test
    public void shouldFailStateTransitionIfItIsFromPendingToPending(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.PENDENTE);

        Assertions.assertThrows(IllegalStateException.class, () -> order.pending(), "Transição de PENDENTE para PENDENTE é inválida!");
    }

    @Test
    public void shouldDoStateTransitionIfItIsFromPendingToAwaitingForPayment(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.PENDENTE);

        order.awaitForPayment();

        Assertions.assertEquals(order.getStatus(), Status.AGUARDANDO_PAGAMENTO);
    }

    @Test
    public void shouldDoStateTransitionIfItIsFromPendingToCanceled(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.PENDENTE);

        order.canceled();

        Assertions.assertEquals(order.getStatus(), Status.CANCELADO);
    }

    @Test
    public void shouldDoStateTransitionIfItIsFromAwaitingForPaymentToCanceled(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.AGUARDANDO_PAGAMENTO);

        order.canceled();

        Assertions.assertEquals(order.getStatus(), Status.CANCELADO);
    }

    @Test
    public void shouldDoStateTransitionIfItIsFromAwaitingForPaymentToFinished(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.AGUARDANDO_PAGAMENTO);

        order.finish();

        Assertions.assertEquals(order.getStatus(), Status.CONCLUIDO);
    }

    @Test
    public void shouldFailStateTransitionIfItIsFromAwaitingForPaymentToPending(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.AGUARDANDO_PAGAMENTO);

        Assertions.assertThrows(IllegalStateException.class, () -> order.pending(), "Transição de AGUARDANDO_PAGAMENTO para PENDENTE é inválida!");
    }

    @Test
    public void shouldFailStateTransitionIfItIsFromAwaitingForPaymentToAwaitingForPayment(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.AGUARDANDO_PAGAMENTO);

        Assertions.assertThrows(IllegalStateException.class, () -> order.awaitForPayment(), "Transição de AGUARDANDO_PAGAMENTO para AGUARDANDO_PAGAMENTO é inválida!");
    }

    @Test
    public void shouldFailStateTransitionIfItIsFromFinishedToAwaitingForPayment(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.CONCLUIDO);

        Assertions.assertThrows(IllegalStateException.class, () -> order.awaitForPayment(), "Transição de CONCLUIDO para AGUARDANDO_PAGAMENTO é inválida!");
    }

    @Test
    public void shouldFailStateTransitionIfItIsFromFinishedToPending(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.CONCLUIDO);

        Assertions.assertThrows(IllegalStateException.class, () -> order.pending(), "Transição de CONCLUIDO para PENDENTE é inválida!");
    }

    @Test
    public void shouldFailStateTransitionIfItIsFromFinishedToCanceled(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.CONCLUIDO);

        Assertions.assertThrows(IllegalStateException.class, () -> order.canceled(), "Transição de CONCLUIDO para CANCELADO é inválida!");
    }

    @Test
    public void shouldFailStateTransitionIfItIsFromFinishedToFinished(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.CONCLUIDO);

        Assertions.assertThrows(IllegalStateException.class, () -> order.finish(), "Transição de CONCLUIDO para CONCLUIDO é inválida!");
    }

    @Test
    public void shouldFailStateTransitionIfItIsFromCanceledToFinished(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.CANCELADO);

        Assertions.assertThrows(IllegalStateException.class, () -> order.finish(), "Transição de CANCELADO para CONCLUIDO é inválida!");
    }

    @Test
    public void shouldFailStateTransitionIfItIsFromCanceledToCanceled(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.CANCELADO);

        Assertions.assertThrows(IllegalStateException.class, () -> order.canceled(), "Transição de CANCELADO para CANCELADO é inválida!");
    }

    @Test
    public void shouldFailStateTransitionIfItIsFromCanceledToAwaitingForPayment(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.CANCELADO);

        Assertions.assertThrows(IllegalStateException.class, () -> order.awaitForPayment(), "Transição de CANCELADO para AGUARDANDO_PAGAMENTO é inválida!");
    }

    @Test
    public void shouldDoStateTransitionIfItIsFromCanceledToPending(){
        Order order = new Order();
        order.setOrderNumber(1L);
        order.setOrderValue(BigDecimal.valueOf(10));
        order.setClient("Matheus");
        order.setDate(LocalDateTime.now());
        order.setPaymentType(PaymentType.PIX);
        order.setStatus(Status.CANCELADO);

        order.pending();

        Assertions.assertEquals(order.getStatus(), Status.PENDENTE);
    }
}
