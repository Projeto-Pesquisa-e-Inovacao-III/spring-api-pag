package com.csf.pagmodulo.infrastructure.pagbank.dto.common;

import java.util.List;

public record PagBankConfigDto(
        List<String> notificationWebhookUrls,
        List<String> paymentNotificationUrls,
        List<String> paymentMethod,
        String softDescriptor,
        String returnUrl,
        String redirectUrl,
        Boolean customerModifiable
) {
}
