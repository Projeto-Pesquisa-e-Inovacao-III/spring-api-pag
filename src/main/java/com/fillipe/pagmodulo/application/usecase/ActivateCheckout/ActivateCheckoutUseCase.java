package com.fillipe.pagmodulo.application.usecase.ActivateCheckout;

import com.fillipe.pagmodulo.application.dto.external.ExtActivationCheckoutDto;
import com.fillipe.pagmodulo.application.mapper.LinkMapper;
import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;
import com.fillipe.pagmodulo.domain.checkout.exception.CheckoutNotFoundException;
import com.fillipe.pagmodulo.domain.checkout.exception.enums.CheckoutNotFoundReason;
import com.fillipe.pagmodulo.domain.checkout.port.out.CheckoutGateway;
import com.fillipe.pagmodulo.domain.checkout.port.out.CheckoutRepository;

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
                .orElseThrow(() -> new CheckoutNotFoundException(uuid, CheckoutNotFoundReason.NOT_EXISTS));

        persistence.validateCanBeActivated();

        ExtActivationCheckoutDto activated = checkoutGateway.activate(persistence);
        persistence.updateStatus(activated.status());
        persistence.updateLinks(LinkMapper.toListDomain(activated.links()));
        return checkoutRepository.save(persistence);
    }
}
