package com.csf.api_pag.presentation.dto.checkout.response;

import com.csf.api_pag.domain.checkout.enums.CheckoutStatus;

import java.util.UUID;

public record ResInactivateCheckoutDto(
        UUID uuid,
        CheckoutStatus status
) {
}
