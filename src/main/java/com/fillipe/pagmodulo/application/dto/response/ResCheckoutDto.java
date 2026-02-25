package com.fillipe.pagmodulo.application.dto.response;

import com.fillipe.pagmodulo.application.dto.CustomerDto;
import com.fillipe.pagmodulo.application.dto.ItemDto;
import com.fillipe.pagmodulo.application.dto.LinkDto;
import com.fillipe.pagmodulo.application.dto.PaymentMethodDto;
import com.fillipe.pagmodulo.domain.checkout.entity.CheckoutStatus;

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
        String softDescriptor,
        String redirectUrl,
        String returnUrl,
        List<String> notificationUrls,
        List<String> paymentNotificationUrls,
        Integer additionalAmount,
        Integer discountAmount,
        List<LinkDto> links
) {}


