package com.csf.api_pag.presentation.dto.checkout.response;

import java.time.OffsetDateTime;

public record ResCheckoutCreatedDto(
        String id,
        String status,
        OffsetDateTime createdAt,
        String payLink
) {}
