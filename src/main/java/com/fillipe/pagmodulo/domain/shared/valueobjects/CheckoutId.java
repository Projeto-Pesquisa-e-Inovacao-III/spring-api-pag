package com.fillipe.pagmodulo.domain.shared.valueobjects;

import com.fillipe.pagmodulo.domain.shared.exceptions.InvalidFieldException;

import java.util.UUID;

public record CheckoutId(UUID value) {
    public CheckoutId {
        if (value == null) throw new InvalidFieldException("CheckoutId.value", "o value não pode ser nulo");
    }
}
