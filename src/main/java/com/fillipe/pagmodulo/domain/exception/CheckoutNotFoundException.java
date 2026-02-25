package com.fillipe.pagmodulo.domain.exception;

import com.fillipe.pagmodulo.domain.exception.enums.CheckoutNotFoundReason;

import java.util.UUID;

public class CheckoutNotFoundException extends RuntimeException {
    private final UUID uuid;
    private final CheckoutNotFoundReason reason;

    public CheckoutNotFoundException(UUID uuid, CheckoutNotFoundReason reason) {
        super(String.format("Checkout %s não encontrado: %s", uuid, reason.getMessage()));
        this.uuid = uuid;
        this.reason = reason;
    }

    public UUID getUuid() {
        return uuid;
    }

    public CheckoutNotFoundReason getReason() {
        return reason;
    }
}

