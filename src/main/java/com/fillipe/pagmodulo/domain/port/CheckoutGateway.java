package com.fillipe.pagmodulo.domain.port;

import com.fillipe.pagmodulo.domain.entity.Checkout;

public interface CheckoutGateway {
    Checkout register(Checkout checkout);
}
