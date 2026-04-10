package com.csf.pagmodulo.infrastructure.config;

import com.csf.pagmodulo.application.usecase.PaymentOrderWebhook.PaymentOrderWebhookUseCase;
import com.csf.pagmodulo.domain.checkout.port.out.CheckoutRepository;
import com.csf.pagmodulo.domain.order.port.OrderRepository;
import com.csf.pagmodulo.infrastructure.event.http.EventPublisherHttpAdapter;
import com.csf.pagmodulo.infrastructure.event.rabbit.EventPublisherRabbitAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderUseCaseConfig {

    @Bean
    public PaymentOrderWebhookUseCase paymentOrderWebhookUseCase(
        OrderRepository orderRepository,
        EventPublisherRabbitAdapter httpEventPublisher,
        CheckoutRepository checkoutRepository
    ) {
        return new PaymentOrderWebhookUseCase(orderRepository, httpEventPublisher, checkoutRepository);
    }
}
