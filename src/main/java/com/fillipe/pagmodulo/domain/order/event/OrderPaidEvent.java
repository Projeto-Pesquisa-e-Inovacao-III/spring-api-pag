package com.fillipe.pagmodulo.domain.order.event;

import com.fillipe.pagmodulo.domain.shared.valueobjects.ChargeId;
import com.fillipe.pagmodulo.domain.shared.valueobjects.CheckoutId;
import com.fillipe.pagmodulo.domain.shared.valueobjects.OrderId;

import java.time.OffsetDateTime;

public record OrderPaidEvent(
        OrderId orderId,
        CheckoutId checkoutId,
        String gatewayOrderId,
        ChargeId chargeId,
        OffsetDateTime paidAt,
        OffsetDateTime occurredAt
) {
}

