package com.csf.pagmodulo.domain.checkout.exception;

import com.csf.pagmodulo.domain.checkout.enums.CheckoutStatus;
import com.csf.pagmodulo.domain.shared.valueobjects.CheckoutId;

import java.util.UUID;

public class CheckoutInvalidStatusException extends RuntimeException {
    public CheckoutInvalidStatusException(CheckoutId checkoutId, CheckoutStatus statusAtual, CheckoutStatus statusEsperado, String operacao) {
        super(String.format(
                "Operação '%s' bloqueada para o Checkout %s. Status atual: %s, Status esperado: %s",
                operacao,
                checkoutId.value(),
                statusAtual.name(),
                statusEsperado.name()
        ));
    }

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
