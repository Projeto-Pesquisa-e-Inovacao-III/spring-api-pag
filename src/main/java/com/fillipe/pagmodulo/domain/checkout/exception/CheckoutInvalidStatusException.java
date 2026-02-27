package com.fillipe.pagmodulo.domain.checkout.exception;

import com.fillipe.pagmodulo.domain.checkout.entity.CheckoutStatus;

import java.util.UUID;

public class CheckoutInvalidStatusException extends RuntimeException {
    public CheckoutInvalidStatusException(UUID uuid, CheckoutStatus statusAtual, CheckoutStatus statusEsperado, String operacao) {
        super(String.format(
                "Operação '%s' bloqueada para o Checkout %s. Status atual: %s, Status esperado: %s",
                operacao,
                uuid,
                statusAtual.name(),
                statusEsperado.name()
        ));
    }
}
