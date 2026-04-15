package com.csf.pagmodulo.application.usecase.PaymentOrderWebhook;

import com.csf.pagmodulo.domain.order.entity.Charge;
import com.csf.pagmodulo.domain.order.entity.Order;
import com.csf.pagmodulo.domain.order.event.OrderPaidEvent;
import com.csf.pagmodulo.domain.order.port.OrderRepository;
import com.csf.pagmodulo.domain.shared.event.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PaymentOrderWebhookUseCase {

    private static final Logger log = LoggerFactory.getLogger(PaymentOrderWebhookUseCase.class);
    private final OrderRepository orderRepository;
    private final EventPublisher publisher;

    public PaymentOrderWebhookUseCase(OrderRepository orderRepository, EventPublisher publisher) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    public void execute(PaymentOrderCommand cmd) {

        // Mudar isso quando for para cartão de credito.
        if (orderRepository.existsByGatewayOrderId(cmd.gatewayOrderId())) {
            log.info("Pedido com gatewayOrderId {} já existe. Pulando processamento.", cmd.gatewayOrderId());
            return;
        }

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

        // TODO: Validar o customer do checkout bate com o do order (tqxId == taxId)

        orderRepository.save(order);
        List<OrderPaidEvent> events = order.getPaidEvents();

        if(!events.isEmpty()){
            publisher.publishAll(events);
        }
    }
}
