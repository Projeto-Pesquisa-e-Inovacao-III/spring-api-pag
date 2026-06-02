package com.csf.api_pag.domain.shared.event;

import java.time.OffsetDateTime;

public class OrderPaidEvent implements  DomainEvent{
    private final String orderId;
    private   final OffsetDateTime occurredOn;

    public OrderPaidEvent(String orderId, OffsetDateTime occurredOn) {
        this.orderId = orderId;
        this.occurredOn = OffsetDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }


    @Override
    public OffsetDateTime occurredOn() {
        return occurredOn;
    }
}
