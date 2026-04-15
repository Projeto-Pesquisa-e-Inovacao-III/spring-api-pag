package com.csf.api_pag.domain.checkout.valueobject.paymentMethod;

import com.csf.api_pag.domain.shared.exceptions.InvalidFieldException;

import java.util.List;

final class PaymentConfigValidator {

    private PaymentConfigValidator() {}

    static void validate(List<PaymentConfigOption> options) {
        int limit = 0;
        int interestFree = 0;

        for (PaymentConfigOption option : options) {
            switch (option.option()) {
                case INSTALLMENTS_LIMIT -> {
                    if (limit > 0) throw new InvalidFieldException(
                            "paymentConfig", "opções de configuração duplicadas não são permitidas");
                    limit = option.value();
                }
                case INTEREST_FREE_INSTALLMENTS -> {
                    if (interestFree > 0) throw new InvalidFieldException(
                            "paymentConfig", "opções de configuração duplicadas não são permitidas");
                    interestFree = option.value();
                }
            }
        }

        if (limit > 0 && interestFree > 0 && interestFree > limit)
            throw new InvalidFieldException("paymentConfig",
                    "INTEREST_FREE_INSTALLMENTS não pode ser maior que INSTALLMENTS_LIMIT");
    }

}
