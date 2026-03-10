package com.fillipe.pagmodulo.application.dto.checkout.response;

import com.fillipe.pagmodulo.domain.checkout.enums.CheckoutStatus;

import java.util.UUID;

public record ResActivateCheckoutDto(
        UUID uuid,
        CheckoutStatus status
) {
}
