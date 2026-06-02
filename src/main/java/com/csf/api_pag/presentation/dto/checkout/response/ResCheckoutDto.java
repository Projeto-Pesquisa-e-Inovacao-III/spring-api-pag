package com.csf.api_pag.presentation.dto.checkout.response;

import com.csf.api_pag.presentation.dto.shared.CustomerDto;
import com.csf.api_pag.presentation.dto.shared.ItemDto;
import com.csf.api_pag.domain.checkout.enums.CheckoutStatus;

import java.time.OffsetDateTime;
import java.util.List;

public record ResCheckoutDto(
        String id,
        String externalCheckoutId,
        CheckoutStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime expirationDate,
        CustomerDto customer,
        List<ItemDto> items,
        Integer additionalAmount,
        Integer discountAmount
) {}


