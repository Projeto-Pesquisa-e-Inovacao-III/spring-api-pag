package com.csf.api_pag.domain.shared.valueobjects;

import com.csf.api_pag.domain.shared.exceptions.InvalidFieldException;

import java.util.UUID;

public record OrderId(UUID value) {
    public OrderId {
        if (value == null) throw new InvalidFieldException("OrderId.value", "o value não deve ser nulo");
    }

    public static OrderId generate() {
        return new OrderId(UUID.randomUUID());
    }
}
