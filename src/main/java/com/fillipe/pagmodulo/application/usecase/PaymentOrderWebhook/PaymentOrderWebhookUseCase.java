package com.fillipe.pagmodulo.application.usecase.PaymentOrderWebhook;

import com.fillipe.pagmodulo.domain.order.entity.Charge;
import com.fillipe.pagmodulo.domain.order.entity.Order;
import com.fillipe.pagmodulo.domain.order.port.OrderRepository;

import java.util.List;

public class PaymentOrderWebhookUseCase {

    private final OrderRepository orderRepository;

    public PaymentOrderWebhookUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(PaymentOrderCommand cmd) {

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

        Order order = Order.newOrder()
                .checkoutId(cmd.checkoutId())
                .gatewayOrderId(cmd.gatewayOrderId())
                .createdAt(cmd.createdAt())
                .customer(cmd.customer())
                .items(cmd.items())
                .charges(charges)
                .build();

        // TODO: Validar se o checkout existe no BD ou no PagBank
        // TODO: Validar o customer do checkout bate com o do order (tqxId == taxId)

        orderRepository.save(order);
    }
}
