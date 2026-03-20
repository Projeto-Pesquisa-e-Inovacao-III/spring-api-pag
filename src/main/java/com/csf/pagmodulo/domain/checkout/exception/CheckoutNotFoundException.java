package com.csf.pagmodulo.domain.checkout.exception;

import com.csf.pagmodulo.domain.shared.valueobjects.CheckoutId;

import java.util.UUID;

public class CheckoutNotFoundException extends RuntimeException {
    public CheckoutNotFoundException(CheckoutId checkoutId, String operacao) {
        super(String.format("Checkout %s não encontrado durante a operacao '%s' ", checkoutId.value(), operacao));
    }

    public CheckoutNotFoundException(UUID uuid, String operacao) {
        super(String.format("Checkout %s não encontrado durante a operacao '%s' ", uuid, operacao));
    }
}

