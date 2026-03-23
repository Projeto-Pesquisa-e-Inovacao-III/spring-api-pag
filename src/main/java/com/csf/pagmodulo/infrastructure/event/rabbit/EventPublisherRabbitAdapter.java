package com.csf.pagmodulo.infrastructure.event.rabbit;

import com.csf.pagmodulo.domain.shared.event.DomainEvent;
import com.csf.pagmodulo.domain.shared.event.EventPublisher;
import com.csf.pagmodulo.domain.order.event.OrderPaidEvent;
import com.csf.pagmodulo.infrastructure.event.rabbit.dto.OrderPaidMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventPublisherRabbitAdapter implements EventPublisher {
    private static final String EXCHANGE = "order_exchange";
    private static final String ROUTING_KEY = "order.paid";
    private final RabbitTemplate rabbitTemplate;

    public EventPublisherRabbitAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(DomainEvent event) {
        if (event instanceof OrderPaidEvent orderPaidEvent) {
            OrderPaidMessage orderPaidMessage = new OrderPaidMessage(
                    orderPaidEvent.orderId(),
                    orderPaidEvent.checkoutId(),
                    orderPaidEvent.gatewayOrderId(),
                    orderPaidEvent.chargeId(),
                    orderPaidEvent.paidAt(),
                    orderPaidEvent.occurredOn()
            );
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
