package com.csf.pagmodulo.infrastructure.event.rabbit.dto;

public record OrderPaidMessage (
        String orderId,
        PaymentStatus status
){ }
