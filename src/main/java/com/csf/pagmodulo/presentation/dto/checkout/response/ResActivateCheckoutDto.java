package com.csf.pagmodulo.presentation.dto.checkout.response;

import com.csf.pagmodulo.domain.checkout.enums.CheckoutStatus;

import java.util.UUID;

public record ResActivateCheckoutDto(
        UUID uuid,
        CheckoutStatus status
) {
}
