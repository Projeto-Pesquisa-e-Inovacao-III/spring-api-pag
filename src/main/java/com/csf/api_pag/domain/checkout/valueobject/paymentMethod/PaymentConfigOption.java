package com.csf.api_pag.domain.checkout.valueobject.paymentMethod;

import com.csf.api_pag.domain.shared.exceptions.InvalidFieldException;

public record PaymentConfigOption(
        PaymentConfigOptionType option,
        int value
) {
    public PaymentConfigOption {
        if (value <= 0)
            throw new InvalidFieldException("paymentConfigOption.value", "deve ser maior que zero");
    }
}
