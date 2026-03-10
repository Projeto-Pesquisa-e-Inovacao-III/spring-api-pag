package com.fillipe.pagmodulo.application.dto.checkout;

import com.fillipe.pagmodulo.domain.checkout.valueobject.paymentMethod.PaymentConfigOptionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentConfigOptionDto(
        @NotNull(message = "O tipo de configuração é obrigatório")
        PaymentConfigOptionType option,

        @Positive(message = "O valor da configuração deve ser maior que zero")
        int value
) {}

