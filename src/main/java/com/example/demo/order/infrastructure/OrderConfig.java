package com.example.demo.order.infrastructure;

import com.example.demo.order.adapters.JpaOrderService;
import com.example.demo.order.usecases.interactorsImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    @Autowired
    private JpaOrderService orderService;

    @Bean
    public CreateOrderInteractorImpl createOrderInteractor(){
        return new CreateOrderInteractorImpl(orderService);
    }

    @Bean
    public FindOrderByNumberInteractorImpl findOrderByNumberInteractor() { return new FindOrderByNumberInteractorImpl(orderService); }

    @Bean
    public AddProductInteractorImpl addProductInteractor() { return new AddProductInteractorImpl(orderService); }

    @Bean
    public FinishOrderInteractorImpl finishOrderInteractor() { return new FinishOrderInteractorImpl(orderService); }

    @Bean
    public AwaitForPaymentInteractorImpl awaitForPaymentInteractor() { return new AwaitForPaymentInteractorImpl(orderService); }
}
