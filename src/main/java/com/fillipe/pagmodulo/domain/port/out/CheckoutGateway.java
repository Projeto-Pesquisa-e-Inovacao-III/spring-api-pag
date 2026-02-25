package com.fillipe.pagmodulo.domain.port.out;

import com.fillipe.pagmodulo.domain.entity.Checkout;

public interface CheckoutGateway {
    Checkout register(Checkout checkout);

    Checkout sync(String gatewayId);
}
