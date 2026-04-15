package com.csf.api_pag.application.usecase.ActivateCheckout;

import com.csf.api_pag.presentation.dto.checkout.external.ExtActivationCheckoutDto;
import com.csf.api_pag.domain.checkout.entity.Checkout;
import com.csf.api_pag.domain.checkout.exception.CheckoutNotFoundException;
import com.csf.api_pag.domain.checkout.port.out.CheckoutGateway;
import com.csf.api_pag.domain.checkout.port.out.CheckoutRepository;

import java.util.UUID;

public class ActivateCheckoutUseCase {
    private final CheckoutRepository checkoutRepository;
    private final CheckoutGateway checkoutGateway;

    public ActivateCheckoutUseCase(
            CheckoutGateway checkoutGateway,
            CheckoutRepository checkoutRepository
    ) {
        this.checkoutRepository = checkoutRepository;
        this.checkoutGateway = checkoutGateway;
    }

    public Checkout execute(UUID uuid) {
        Checkout persistence = checkoutRepository.findByUuid(uuid)
                .orElseThrow(() -> new CheckoutNotFoundException(uuid, "ativa"));

        persistence.validateCanBeActivated();

        ExtActivationCheckoutDto activated = checkoutGateway.activate(persistence);
        persistence.updateStatus(activated.status());
        return checkoutRepository.save(persistence);
    }
}
