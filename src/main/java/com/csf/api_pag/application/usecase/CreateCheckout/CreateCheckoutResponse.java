package com.csf.api_pag.application.usecase.CreateCheckout;

import com.csf.api_pag.domain.checkout.entity.Checkout;

public record CreateCheckoutResponse(
        Checkout checkout,
        String payLink
) {
}
