package com.csf.pagmodulo.application.usecase.InactivateCheckout;

import com.csf.pagmodulo.application.dto.checkout.external.ExtInactivationCheckoutDto;
import com.csf.pagmodulo.domain.checkout.entity.Checkout;
import com.csf.pagmodulo.domain.checkout.exception.CheckoutNotFoundException;
import com.csf.pagmodulo.domain.checkout.port.out.CheckoutGateway;
import com.csf.pagmodulo.domain.checkout.port.out.CheckoutRepository;

import java.util.UUID;

public class InactivateCheckoutUseCase {
    private final CheckoutRepository checkoutRepository;
    private final CheckoutGateway checkoutGateway;

    public InactivateCheckoutUseCase(
            CheckoutGateway checkoutGateway,
            CheckoutRepository checkoutRepository
    ) {
        this.checkoutRepository = checkoutRepository;
        this.checkoutGateway = checkoutGateway;
    }

    public Checkout execute(UUID uuid) {
        Checkout persistence = checkoutRepository.findByUuid(uuid)
                .orElseThrow(() -> new CheckoutNotFoundException(uuid, "inativar"));

        persistence.validateCanBeInactivated();

        ExtInactivationCheckoutDto inactivated = checkoutGateway.inactivate(persistence);
        persistence.updateStatus(inactivated.status());
        return checkoutRepository.save(persistence);
    }
}
