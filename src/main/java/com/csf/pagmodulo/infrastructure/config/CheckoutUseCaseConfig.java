package com.csf.pagmodulo.infrastructure.config;

import com.csf.pagmodulo.application.usecase.ActivateCheckout.ActivateCheckoutUseCase;
import com.csf.pagmodulo.application.usecase.InactivateCheckout.InactivateCheckoutUseCase;
import com.csf.pagmodulo.application.usecase.GetCheckout.GetCheckoutUseCase;
import com.csf.pagmodulo.domain.checkout.port.out.CheckoutGateway;
import com.csf.pagmodulo.domain.checkout.port.out.CheckoutRepository;
import com.csf.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutUseCase;
import com.csf.pagmodulo.application.usecase.UpdateCheckoutStatus.UpdateCheckoutStatusUseCase;
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
