package com.fillipe.pagmodulo.infrastructure.config;

import com.fillipe.pagmodulo.domain.port.CheckoutGateway;
import com.fillipe.pagmodulo.domain.port.CheckoutRepository;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateCheckoutUseCase createCheckoutUseCase(CheckoutGateway checkoutGateway,
                                                       CheckoutRepository checkoutRepository) {
        return new CreateCheckoutUseCase(checkoutRepository, checkoutGateway);
    }
}

