package com.csf.pagmodulo.application.dto.checkout.response;

import com.csf.pagmodulo.application.dto.shared.CustomerDto;
import com.csf.pagmodulo.application.dto.shared.ItemDto;
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


