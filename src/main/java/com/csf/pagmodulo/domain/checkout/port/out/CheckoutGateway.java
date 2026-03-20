package com.csf.pagmodulo.domain.checkout.port.out;

import com.csf.pagmodulo.application.dto.checkout.external.ExtActivationCheckoutDto;
import com.csf.pagmodulo.application.dto.checkout.external.ExtInactivationCheckoutDto;
import com.csf.pagmodulo.domain.checkout.entity.Checkout;

public interface CheckoutGateway {
    Checkout register(Checkout checkout);

    Checkout sync(String gatewayId);

    ExtActivationCheckoutDto activate(Checkout checkout);

    ExtInactivationCheckoutDto inactivate(Checkout checkout);

    String getGatewayPayUrl(Checkout checkout);
}
