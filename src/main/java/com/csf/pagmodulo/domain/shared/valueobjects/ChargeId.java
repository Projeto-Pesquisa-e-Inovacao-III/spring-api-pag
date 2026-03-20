package com.csf.pagmodulo.domain.shared.valueobjects;

import com.csf.pagmodulo.domain.shared.exceptions.InvalidFieldException;

import java.util.UUID;

public record ChargeId(UUID value) {
    public ChargeId {
        if (value == null) throw new InvalidFieldException("CheckoutId.value", "o value não pode ser nulo");
    }
}
