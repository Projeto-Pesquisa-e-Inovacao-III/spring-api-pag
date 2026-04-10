package com.csf.pagmodulo.domain.order.event;

import com.csf.pagmodulo.domain.shared.event.DomainEvent;
import com.csf.pagmodulo.domain.shared.valueobjects.ChargeId;
import com.csf.pagmodulo.domain.shared.valueobjects.CheckoutId;
import com.csf.pagmodulo.domain.shared.valueobjects.OrderId;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public record OrderPaidEvent(
        OrderId orderId,
        CheckoutId checkoutId,
        String gatewayOrderId,
        String customerId,
        List<String> itensId,
        ChargeId chargeId,
        OffsetDateTime paidAt,
        OffsetDateTime occurredOn
) implements DomainEvent {
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(-3);

    public OrderPaidEvent(OrderId orderId, CheckoutId checkoutId, String gatewayOrderId, String customerId, List<String> itensId, ChargeId chargeId, OffsetDateTime paidAt) {
        this(orderId, checkoutId, gatewayOrderId, customerId,  itensId, chargeId, paidAt, OffsetDateTime.now(ZONE_OFFSET));
    }
}

