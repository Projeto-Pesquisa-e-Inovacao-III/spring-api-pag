package com.fillipe.pagmodulo.application.dto.order.request;

import jakarta.validation.constraints.NotBlank;

public record ReqPaymentResponseDto(
        @NotBlank(message = "O código de resposta é obrigatório")
        String code,

        @NotBlank(message = "A mensagem de resposta é obrigatória")
        String message,

        String reference
) {}

