package com.fillipe.pagmodulo.domain.checkout.exception;

import java.util.UUID;

public class CheckoutExpiredException extends RuntimeException {
    public CheckoutExpiredException(UUID uuid, String operacao) {
        super(String.format(
                "Operação '%s' bloqueada para o Checkout %s por estar com o status EXPIRED",
                operacao, uuid)
        );
    }
}
