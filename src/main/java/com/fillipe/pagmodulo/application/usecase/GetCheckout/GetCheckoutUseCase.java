package com.fillipe.pagmodulo.application.usecase.GetCheckout;

import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;
import com.fillipe.pagmodulo.domain.checkout.exception.CheckoutNotFoundException;
import com.fillipe.pagmodulo.domain.checkout.port.out.CheckoutGateway;
import com.fillipe.pagmodulo.domain.checkout.port.out.CheckoutRepository;

import java.util.UUID;

public class GetCheckoutUseCase {

    private final CheckoutGateway checkoutGateway;
    private final CheckoutRepository checkoutRepository;

    public GetCheckoutUseCase(CheckoutGateway checkoutGateway, CheckoutRepository checkoutRepository) {
        this.checkoutGateway = checkoutGateway;
        this.checkoutRepository = checkoutRepository;
    }

    public Checkout execute(UUID uuid) {
        Checkout persistence = checkoutRepository.findByUuid(uuid)
                .orElseThrow(() -> new CheckoutNotFoundException(uuid, "Get"));

        Checkout synced = checkoutGateway.sync(persistence.getGatewayId());
        persistence.updateStatus(synced.getStatus());

        return checkoutRepository.update(persistence);
    }
}
