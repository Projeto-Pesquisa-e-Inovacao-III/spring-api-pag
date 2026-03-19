package com.fillipe.pagmodulo.domain.order.event;

import com.fillipe.pagmodulo.domain.shared.event.DomainEvent;
import com.fillipe.pagmodulo.domain.shared.valueobjects.ChargeId;
import com.fillipe.pagmodulo.domain.shared.valueobjects.CheckoutId;
import com.fillipe.pagmodulo.domain.shared.valueobjects.OrderId;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public record OrderPaidEvent(
        OrderId orderId,
        CheckoutId checkoutId,
        String gatewayOrderId,
        ChargeId chargeId,
        OffsetDateTime paidAt,
        OffsetDateTime occurredOn
) implements DomainEvent {
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(-3);

    public OrderPaidEvent(OrderId orderId, CheckoutId checkoutId, String gatewayOrderId, ChargeId chargeId, OffsetDateTime paidAt) {
        this(orderId, checkoutId, gatewayOrderId, chargeId, paidAt, OffsetDateTime.now(ZONE_OFFSET));
    }
}

