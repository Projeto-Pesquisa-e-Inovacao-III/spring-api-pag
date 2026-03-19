package com.fillipe.pagmodulo.infrastructure.config;

import com.fillipe.pagmodulo.application.usecase.PaymentOrderWebhook.PaymentOrderWebhookUseCase;
import com.fillipe.pagmodulo.domain.order.port.OrderRepository;
import com.fillipe.pagmodulo.infrastructure.event.EventPublisherHttpAdapter;
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
