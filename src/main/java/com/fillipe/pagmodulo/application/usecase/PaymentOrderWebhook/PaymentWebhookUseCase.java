package com.fillipe.pagmodulo.application.usecase.PaymentOrderWebhook;

import com.fillipe.pagmodulo.domain.order.entity.Charge;
import com.fillipe.pagmodulo.domain.order.entity.Order;
import com.fillipe.pagmodulo.domain.order.port.OrderGateway;

import java.util.List;

public class PaymentWebhookUseCase {

    private final OrderGateway orderGateway;

    public PaymentWebhookUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order execute(PaymentOrderCommand cmd) {

        List<Charge> charges = cmd.charges().stream()
                .map(c -> new Charge(
                        c.id(),
                        c.gatewayId(),
                        c.status(),
                        c.createdAt(),
                        c.paidAt(),
                        c.description(),
                        c.amount(),
                        c.paymentResponse(),
                        c.paymentInstruction()
                ))
                .toList();

        Order order = Order.builder()
                .orderId(cmd.orderId())
                .checkoutId(cmd.checkoutId())
                .gatewayOrderId(cmd.gatewayOrderId())
                .createdAt(cmd.createdAt())
                .customer(cmd.customer())
                .items(cmd.items())
                .charges(charges)
                .build();

        return orderGateway.saveOrder(order);
    }
}
