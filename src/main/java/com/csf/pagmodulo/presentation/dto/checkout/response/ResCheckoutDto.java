package com.csf.pagmodulo.presentation.dto.checkout.response;

import com.csf.pagmodulo.presentation.dto.shared.CustomerDto;
import com.csf.pagmodulo.presentation.dto.shared.ItemDto;
import com.csf.pagmodulo.domain.checkout.enums.CheckoutStatus;

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


