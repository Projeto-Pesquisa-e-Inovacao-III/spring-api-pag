package com.csf.pagmodulo.presentation.dto.shared;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PhoneDto(
        @NotBlank(message = "O código do país é obrigatório")
        @Pattern(regexp = "\\+55", message = "O código do país deve ser +55")
        String country,

        @NotBlank(message = "O código de área é obrigatório")
        @Pattern(regexp = "\\d{2}", message = "O código de área deve ter 2 dígitos")
        String area,

        @NotBlank(message = "O número é obrigatório")
        @Pattern(regexp = "\\d{9}", message = "O número deve ter 9 dígitos")
        String number
) {}

