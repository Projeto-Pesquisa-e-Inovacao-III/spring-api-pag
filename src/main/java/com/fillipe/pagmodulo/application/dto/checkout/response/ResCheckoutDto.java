package com.fillipe.pagmodulo.application.dto.checkout.response;

import com.fillipe.pagmodulo.application.dto.shared.CustomerDto;
import com.fillipe.pagmodulo.application.dto.shared.ItemDto;
import com.fillipe.pagmodulo.application.dto.checkout.PaymentMethodDto;
import com.fillipe.pagmodulo.domain.checkout.enums.CheckoutStatus;

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
        List<PaymentMethodDto> paymentMethods,
        Integer additionalAmount,
        Integer discountAmount
) {}


