package com.csf.api_pag.infrastructure.event.rabbit;

import com.csf.api_pag.domain.shared.event.DomainEvent;
import com.csf.api_pag.domain.shared.event.EventPublisher;
import com.csf.api_pag.domain.order.event.OrderPaidEvent;
import com.csf.api_pag.infrastructure.event.rabbit.dto.OrderPaidMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventPublisherRabbitAdapter implements EventPublisher {
    private static final String EXCHANGE = "order_exchange";
    private static final String ROUTING_KEY = "order.paid";
    private static final Logger log = LoggerFactory.getLogger(EventPublisherRabbitAdapter.class);
    private final RabbitTemplate rabbitTemplate;

    public EventPublisherRabbitAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(DomainEvent event) {
        if (event instanceof OrderPaidEvent orderPaidEvent) {
            OrderPaidMessage orderPaidMessage = new OrderPaidMessage(
                    orderPaidEvent.orderId().value().toString(),
                    orderPaidEvent.checkoutId().value().toString(),
                    orderPaidEvent.gatewayOrderId(),
                    orderPaidEvent.customerId(),
                    orderPaidEvent.itensId(),
                    orderPaidEvent.chargeId().value().toString(),
                    orderPaidEvent.paidAt(),
                    orderPaidEvent.occurredOn()
            );

            log.info("Publishing OrderPaidMessage: orderId={}, checkoutId={}, chargeId={}, itensId={}, exchange={}, routingKey={}",
                    orderPaidMessage.orderId(),
                    orderPaidMessage.checkoutId(),
                    orderPaidMessage.chargeId(),
                    orderPaidMessage.itensId(),
                    EXCHANGE,
                    ROUTING_KEY);

            rabbitTemplate.convertAndSend(
                    EXCHANGE,
                    ROUTING_KEY,
                    orderPaidMessage);
        }
    }

    @Override
    public void publishAll(List<? extends DomainEvent> events) {
        events.forEach(this::publish);
    }
}
