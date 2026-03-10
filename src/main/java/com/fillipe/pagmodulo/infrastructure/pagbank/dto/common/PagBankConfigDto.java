package com.fillipe.pagmodulo.infrastructure.pagbank.dto.common;

import java.util.List;

public record PagBankConfigDto(
        List<String> notificationWebhookUrls,
        List<String> paymentNotificationUrls,
        String softDescriptor,
        String returnUrl,
        String redirectUrl
) {
}
