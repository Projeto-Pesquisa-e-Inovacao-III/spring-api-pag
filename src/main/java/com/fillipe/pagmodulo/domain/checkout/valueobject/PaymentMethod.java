package com.fillipe.pagmodulo.domain.checkout.valueobject;

import java.util.List;

public record PaymentMethod(
        PaymentType type,
        List<PaymentConfigOption> configOptions
) {
    // Todo: validar opções repetidas
    public PaymentMethod(PaymentType type) {
        this(type, List.of());
    }

    public PaymentMethod {
        configOptions = configOptions == null ? List.of() : List.copyOf(configOptions);
    }

    public boolean hasConfigs() {
        return !configOptions.isEmpty();
    }
}