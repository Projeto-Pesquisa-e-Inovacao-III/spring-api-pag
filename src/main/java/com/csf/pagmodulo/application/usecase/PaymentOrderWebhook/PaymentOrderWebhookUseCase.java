package com.csf.pagmodulo.application.usecase.PaymentOrderWebhook;

import com.csf.pagmodulo.domain.checkout.entity.Checkout;
import com.csf.pagmodulo.domain.checkout.exception.CheckoutNotFoundException;
import com.csf.pagmodulo.domain.checkout.port.out.CheckoutRepository;
import com.csf.pagmodulo.domain.order.entity.Charge;
import com.csf.pagmodulo.domain.order.entity.Order;
import com.csf.pagmodulo.domain.order.event.OrderPaidEvent;
import com.csf.pagmodulo.domain.order.port.OrderRepository;
import com.csf.pagmodulo.domain.shared.event.EventPublisher;
import com.csf.pagmodulo.domain.shared.valueobjects.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PaymentOrderWebhookUseCase {

    private static final Logger log = LoggerFactory.getLogger(PaymentOrderWebhookUseCase.class);
    private final OrderRepository orderRepository;
    private final EventPublisher publisher;
    private final CheckoutRepository checkoutRepository;

    public PaymentOrderWebhookUseCase(OrderRepository orderRepository, EventPublisher publisher, CheckoutRepository checkoutRepository) {
        this.orderRepository = orderRepository;
        this.checkoutRepository = checkoutRepository;
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

        Checkout foundCheckout = checkoutRepository.findByUuid(cmd.checkoutId().value())
                .orElseThrow(() -> new CheckoutNotFoundException(cmd.checkoutId(), "Order Creation"));

        Customer customer = cmd.customer().withExternalCustomerId(foundCheckout.getCustomer().externalCustomerId());

        Order order = Order.newOrder()
                .checkoutId(cmd.checkoutId())
                .gatewayOrderId(cmd.gatewayOrderId())
                .createdAt(cmd.createdAt())
                .customer(customer)
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
