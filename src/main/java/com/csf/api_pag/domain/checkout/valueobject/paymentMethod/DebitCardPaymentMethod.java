package com.csf.api_pag.domain.checkout.valueobject.paymentMethod;

import java.util.List;

public record DebitCardPaymentMethod(
        List<PaymentConfigOption> configOptions
) implements ConfigurableCardPaymentMethod {

    public DebitCardPaymentMethod {
        configOptions = configOptions == null ? List.of() : List.copyOf(configOptions);
        PaymentConfigValidator.validate(configOptions);
    }

    public DebitCardPaymentMethod() { this(List.of()); }
}
