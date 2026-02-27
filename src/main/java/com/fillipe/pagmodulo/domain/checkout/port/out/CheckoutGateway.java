package com.fillipe.pagmodulo.domain.checkout.port.out;

import com.fillipe.pagmodulo.application.dto.external.ExtActivationCheckoutDto;
import com.fillipe.pagmodulo.application.dto.external.ExtInactivationCheckoutDto;
import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;

public interface CheckoutGateway {
    Checkout register(Checkout checkout);

    Checkout sync(String gatewayId);

    ExtActivationCheckoutDto activate(Checkout checkout);

    ExtInactivationCheckoutDto inactivate(Checkout checkout);
}
