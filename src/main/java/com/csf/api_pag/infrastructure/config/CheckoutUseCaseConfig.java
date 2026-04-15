package com.csf.api_pag.infrastructure.config;

import com.csf.api_pag.application.usecase.ActivateCheckout.ActivateCheckoutUseCase;
import com.csf.api_pag.application.usecase.InactivateCheckout.InactivateCheckoutUseCase;
import com.csf.api_pag.application.usecase.GetCheckout.GetCheckoutUseCase;
import com.csf.api_pag.domain.checkout.port.out.CheckoutGateway;
import com.csf.api_pag.domain.checkout.port.out.CheckoutRepository;
import com.csf.api_pag.application.usecase.CreateCheckout.CreateCheckoutUseCase;
import com.csf.api_pag.application.usecase.UpdateCheckoutStatus.UpdateCheckoutStatusUseCase;
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

    @Bean
    public UpdateCheckoutStatusUseCase updateCheckoutStatusUseCase(
            CheckoutRepository checkoutRepository
    ) {
        return new UpdateCheckoutStatusUseCase(checkoutRepository);
    }
}
