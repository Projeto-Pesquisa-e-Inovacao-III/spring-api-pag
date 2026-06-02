package com.csf.api_pag.infrastructure.config;

import com.csf.api_pag.application.usecase.PaymentOrderWebhook.PaymentOrderWebhookUseCase;
import com.csf.api_pag.domain.checkout.port.out.CheckoutRepository;
import com.csf.api_pag.domain.order.port.OrderRepository;
import com.csf.api_pag.infrastructure.event.rabbit.EventPublisherRabbitAdapter;
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
