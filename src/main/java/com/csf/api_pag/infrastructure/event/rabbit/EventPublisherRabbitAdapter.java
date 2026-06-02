package com.csf.api_pag.infrastructure.event.rabbit;

import com.csf.api_pag.domain.shared.event.DomainEvent;
import com.csf.api_pag.domain.shared.event.EventPublisher;
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
    private final HmacService HmacService;

    public EventPublisherRabbitAdapter(RabbitTemplate rabbitTemplate,
                                       HmacService HmacService) {
        this.rabbitTemplate = rabbitTemplate;
        this.HmacService = HmacService;
    }

    @Override
    public void publish(DomainEvent event) {
        log.info("Publishing event: " + event.toString(),
                EXCHANGE,
                ROUTING_KEY);

        String idempotencyKey = HmacService.generateForEvent(event);

        rabbitTemplate.convertAndSend(
                EXCHANGE,
                ROUTING_KEY,
                event,
                message -> {
                    message.getMessageProperties().setHeader("x-idempotency-key", idempotencyKey);
                    message.getMessageProperties().setHeader("x-timestamp", event.occurredOn());
                    return message;
                });
    }

    @Override
    public void publishAll(List<? extends DomainEvent> events) {
        events.forEach(this::publish);
    }
}
