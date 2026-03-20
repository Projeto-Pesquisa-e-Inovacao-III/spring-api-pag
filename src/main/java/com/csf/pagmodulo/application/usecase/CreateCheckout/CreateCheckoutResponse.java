package com.csf.pagmodulo.application.usecase.CreateCheckout;

import com.csf.pagmodulo.domain.checkout.entity.Checkout;

public record CreateCheckoutResponse(
        Checkout checkout,
        String payLink
) {
}
