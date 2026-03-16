package com.fillipe.pagmodulo.application.dto.order.request;

public record ReqPagBankNotificationDto(
        String notificationCode,
        String notificationType
) {}

