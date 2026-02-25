package com.fillipe.pagmodulo.domain.checkout.port.out;

import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;

public interface CheckoutGateway {
    Checkout register(Checkout checkout);

    Checkout sync(String gatewayId);
}
