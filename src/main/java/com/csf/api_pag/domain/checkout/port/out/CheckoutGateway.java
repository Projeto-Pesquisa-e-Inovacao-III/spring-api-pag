package com.csf.api_pag.domain.checkout.port.out;

import com.csf.api_pag.presentation.dto.checkout.external.ExtActivationCheckoutDto;
import com.csf.api_pag.presentation.dto.checkout.external.ExtInactivationCheckoutDto;
import com.csf.api_pag.domain.checkout.entity.Checkout;

public interface CheckoutGateway {
    Checkout register(Checkout checkout);

    Checkout sync(String gatewayId);

    ExtActivationCheckoutDto activate(Checkout checkout);

    ExtInactivationCheckoutDto inactivate(Checkout checkout);

    String getGatewayPayUrl(Checkout checkout);
}
