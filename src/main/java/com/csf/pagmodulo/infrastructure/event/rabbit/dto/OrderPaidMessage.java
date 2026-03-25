package com.csf.pagmodulo.infrastructure.event.rabbit.dto;

import com.csf.pagmodulo.domain.shared.valueobjects.ChargeId;
import com.csf.pagmodulo.domain.shared.valueobjects.CheckoutId;
import com.csf.pagmodulo.domain.shared.valueobjects.OrderId;

import java.time.OffsetDateTime;

public record OrderPaidMessage(
        String orderId,
        String checkoutId,
        String gatewayOrderId,
        String chargeId,
        OffsetDateTime paidAt,
        OffsetDateTime occurredOn
) { }
