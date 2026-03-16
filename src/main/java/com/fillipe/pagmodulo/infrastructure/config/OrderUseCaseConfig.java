package com.fillipe.pagmodulo.infrastructure.config;

import com.fillipe.pagmodulo.application.usecase.PaymentOrderWebhook.PaymentOrderWebhookUseCase;
import com.fillipe.pagmodulo.domain.order.port.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderUseCaseConfig {

    @Bean
    public PaymentOrderWebhookUseCase paymentOrderWebhookUseCase(
        OrderRepository orderRepository
    ) {
        return new PaymentOrderWebhookUseCase(orderRepository);
    }
}
