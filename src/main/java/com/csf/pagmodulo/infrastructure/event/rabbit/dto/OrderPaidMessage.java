package com.csf.pagmodulo.infrastructure.event.rabbit.dto;

import com.csf.pagmodulo.domain.shared.valueobjects.ChargeId;
import com.csf.pagmodulo.domain.shared.valueobjects.CheckoutId;
import com.csf.pagmodulo.domain.shared.valueobjects.OrderId;

import java.time.OffsetDateTime;

public record OrderPaidMessage(
        OrderId orderId,
        CheckoutId checkoutId,
        String gatewayOrderId,
        ChargeId chargeId,
        OffsetDateTime paidAt,
        OffsetDateTime occurredOn
) { }
