package com.fillipe.pagmodulo.application.dto.order.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ReqPhoneOrderDto(
        @NotBlank(message = "O código do país é obrigatório")
        String country,

        @NotBlank(message = "O código de área é obrigatório")
        @Pattern(regexp = "\\d{2}", message = "O código de área deve ter 2 dígitos")
        String area,

        @NotBlank(message = "O número é obrigatório")
        @Pattern(regexp = "\\d{9}", message = "O número deve ter 9 dígitos")
        String number,

        String type
) {}

