package com.fillipe.pagmodulo.application.usecase.CreateCheckout;

import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;

public record CreateCheckoutResponse(
        Checkout checkout,
        String payLink
) {
}
