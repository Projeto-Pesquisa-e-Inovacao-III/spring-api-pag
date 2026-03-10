package com.fillipe.pagmodulo.domain.shared.valueobjects;

import com.fillipe.pagmodulo.domain.shared.exceptions.InvalidFieldException;

import java.util.UUID;

public record OrderId(UUID value) {
    public OrderId {
        if (value == null) throw new InvalidFieldException("OrderId.value", "o value não deve ser nulo");
    }
}
