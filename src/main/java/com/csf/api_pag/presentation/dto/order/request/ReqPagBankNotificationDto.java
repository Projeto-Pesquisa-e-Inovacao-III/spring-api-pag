package com.csf.api_pag.presentation.dto.order.request;

public record ReqPagBankNotificationDto(
        String notificationCode,
        String notificationType
) {}

