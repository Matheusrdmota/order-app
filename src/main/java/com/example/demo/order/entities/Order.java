package com.example.demo.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long orderNumber;
    private BigDecimal orderValue;
    private String client;
    private LocalDateTime date;
    private Status status;
    private List<Product> products;
    private PaymentType paymentType;
    private BigDecimal discount;

    public boolean isValidDiscount(){
        return this.discount.compareTo(BigDecimal.valueOf(0.2)) == -1;
    }

    public void setDiscountIfPaymentTypeIsDinheiroOrPix(){
        if(paymentType.equals(PaymentType.PIX) || paymentType.equals(PaymentType.DINHEIRO)){
            this.discount = BigDecimal.valueOf(0.15);
            return;
        }
        this.discount = BigDecimal.ZERO;
    }

    public void finish(){
        this.getStatus().changeToFinished(this);
    }

    public void awaitForPayment(){
        this.getStatus().changeToAwaitingForPayment(this);
    }

    public void pending(){
        this.getStatus().changeToPending(this);
    }

    public void canceled(){
        this.getStatus().changeToCanceled(this);
    }
}
