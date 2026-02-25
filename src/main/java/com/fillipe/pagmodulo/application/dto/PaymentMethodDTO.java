package com.fillipe.pagmodulo.application.dto;

import com.fillipe.pagmodulo.domain.valueobject.PaymentType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PaymentMethodDto(
        @NotNull(message = "O tipo de pagamento é obrigatório")
        PaymentType type,

        @Valid
        List<PaymentConfigOptionDto> configOptions
) {}

