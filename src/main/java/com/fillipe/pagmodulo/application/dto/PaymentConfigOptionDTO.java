package com.fillipe.pagmodulo.application.dto;

import com.fillipe.pagmodulo.domain.valueobject.PaymentConfigOptionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentConfigOptionDto(
        @NotNull(message = "O tipo de configuração é obrigatório")
        PaymentConfigOptionType type,

        @NotBlank(message = "O valor da configuração é obrigatório")
        String value
) {}

