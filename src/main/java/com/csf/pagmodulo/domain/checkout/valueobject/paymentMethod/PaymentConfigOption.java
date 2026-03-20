package com.csf.pagmodulo.domain.checkout.valueobject.paymentMethod;

import com.csf.pagmodulo.domain.shared.exceptions.InvalidFieldException;

public record PaymentConfigOption(
        PaymentConfigOptionType option,
        int value
) {
    public PaymentConfigOption {
        if (value <= 0)
            throw new InvalidFieldException("paymentConfigOption.value", "deve ser maior que zero");
    }
}
