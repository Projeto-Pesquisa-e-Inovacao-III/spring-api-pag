package com.csf.pagmodulo.application.usecase.ActivateCheckout;

import com.csf.pagmodulo.presentation.dto.checkout.external.ExtActivationCheckoutDto;
import com.csf.pagmodulo.domain.checkout.entity.Checkout;
import com.csf.pagmodulo.domain.checkout.exception.CheckoutNotFoundException;
import com.csf.pagmodulo.domain.checkout.port.out.CheckoutGateway;
import com.csf.pagmodulo.domain.checkout.port.out.CheckoutRepository;

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
