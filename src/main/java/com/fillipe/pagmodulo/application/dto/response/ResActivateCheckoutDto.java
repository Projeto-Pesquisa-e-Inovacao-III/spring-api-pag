package com.fillipe.pagmodulo.application.dto.response;

import com.fillipe.pagmodulo.domain.checkout.entity.CheckoutStatus;

import java.util.UUID;

public record ResActivateCheckoutDto(
        UUID uuid,
        CheckoutStatus status
) {
}
