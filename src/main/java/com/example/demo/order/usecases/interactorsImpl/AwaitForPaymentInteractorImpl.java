package com.example.demo.order.usecases.interactorsImpl;

import com.example.demo.order.entities.Order;
import com.example.demo.order.entities.Status;
import com.example.demo.order.usecases.dto.ResponseOrderDTO;
import com.example.demo.order.usecases.gateways.OrderGateway;
import com.example.demo.order.usecases.interactors.UpdateOrderStatusInteractor;

public class AwaitForPaymentInteractorImpl extends UpdateOrderStatusInteractor {

    public AwaitForPaymentInteractorImpl(OrderGateway gateway){
        super(gateway);
    }

    @Override
    public void updateStatus(Long orderNumber, Status status) {
        if(!status.equals(Status.AGUARDANDO_PAGAMENTO)){
            return;
        }

        this.checkIfOrderExists(orderNumber);
        Order order = this.convertToOrderEntity(orderDTO);

        order.awaitForPayment();

        this.saveOrderUpdated(order);
    }
}
