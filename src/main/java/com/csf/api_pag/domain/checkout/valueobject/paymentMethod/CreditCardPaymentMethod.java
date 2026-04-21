package com.csf.api_pag.domain.checkout.valueobject.paymentMethod;


import java.util.List;

public record CreditCardPaymentMethod(
        List<PaymentConfigOption> configOptions
) implements ConfigurableCardPaymentMethod {

    public CreditCardPaymentMethod {
        configOptions = configOptions == null ? List.of() : List.copyOf(configOptions);
        PaymentConfigValidator.validate(configOptions);
    }

    public CreditCardPaymentMethod() { this(List.of()); }
}