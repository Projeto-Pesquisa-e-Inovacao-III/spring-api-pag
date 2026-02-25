package com.fillipe.pagmodulo.infrastructure.config;

import com.fillipe.pagmodulo.application.usecase.GetCheckout.GetCheckoutUseCase;
import com.fillipe.pagmodulo.domain.port.out.CheckoutGateway;
import com.fillipe.pagmodulo.domain.port.out.CheckoutRepository;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateCheckoutUseCase createCheckoutUseCase(
            CheckoutGateway checkoutGateway,
            CheckoutRepository checkoutRepository
    ) {
        return new CreateCheckoutUseCase(checkoutGateway, checkoutRepository);
    }

    @Bean
    public GetCheckoutUseCase getCheckoutUseCase(
            CheckoutGateway checkoutGateway,
            CheckoutRepository checkoutRepository
    ) {
        return new GetCheckoutUseCase(checkoutGateway, checkoutRepository);
    }
}

