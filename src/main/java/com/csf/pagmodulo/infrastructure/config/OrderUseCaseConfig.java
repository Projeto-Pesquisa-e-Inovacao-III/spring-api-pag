package com.csf.pagmodulo.infrastructure.config;

import com.csf.pagmodulo.application.usecase.PaymentOrderWebhook.PaymentOrderWebhookUseCase;
import com.csf.pagmodulo.domain.order.port.OrderRepository;
import com.csf.pagmodulo.infrastructure.event.EventPublisherHttpAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderUseCaseConfig {

    @Bean
    public PaymentOrderWebhookUseCase paymentOrderWebhookUseCase(
        OrderRepository orderRepository,
        EventPublisherHttpAdapter httpEventPublisher
    ) {
        return new PaymentOrderWebhookUseCase(orderRepository, httpEventPublisher);
    }
}
