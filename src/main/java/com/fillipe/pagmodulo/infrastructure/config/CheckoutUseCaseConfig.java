package com.fillipe.pagmodulo.infrastructure.config;

import com.fillipe.pagmodulo.application.usecase.ActivateCheckout.ActivateCheckoutUseCase;
import com.fillipe.pagmodulo.application.usecase.InactivateCheckout.InactivateCheckoutUseCase;
import com.fillipe.pagmodulo.application.usecase.GetCheckout.GetCheckoutUseCase;
import com.fillipe.pagmodulo.domain.checkout.port.out.CheckoutGateway;
import com.fillipe.pagmodulo.domain.checkout.port.out.CheckoutRepository;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckoutUseCaseConfig {

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

    @Bean
    public ActivateCheckoutUseCase activateCheckoutUseCase(
            CheckoutGateway checkoutGateway,
            CheckoutRepository checkoutRepository
    ) {
        return new ActivateCheckoutUseCase(checkoutGateway, checkoutRepository);
    }


    @Bean
    public InactivateCheckoutUseCase inactivateCheckoutUseCase(
            CheckoutGateway checkoutGateway,
            CheckoutRepository checkoutRepository
    ) {
        return new InactivateCheckoutUseCase(checkoutGateway, checkoutRepository);
    }
}

