package com.csf.api_pag.domain.checkout.exception;

import com.csf.api_pag.domain.shared.valueobjects.CheckoutId;

import java.util.UUID;

public class CheckoutExpiredException extends RuntimeException {
    public CheckoutExpiredException(CheckoutId checkoutId, String operacao) {
        super(String.format(
                "Operação '%s' bloqueada para o Checkout %s por estar com o status EXPIRED",
                operacao, checkoutId.value())
        );
    }

    public CheckoutExpiredException(UUID uuid, String operacao) {
        super(String.format(
                "Operação '%s' bloqueada para o Checkout %s por estar com o status EXPIRED",
                operacao, uuid)
        );
    }
}
