package com.fillipe.pagmodulo.application.dto.order.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReqAmountDto(
        @NotNull(message = "O valor é obrigatório")
        @Min(value = 1, message = "O valor deve ser no mínimo 1")
        Integer value,

        @NotBlank(message = "A moeda é obrigatória")
        @Size(min = 3, max = 3, message = "A moeda deve ter 3 caracteres")
        String currency,

        @NotNull(message = "O resumo é obrigatório")
        @Valid
        ReqSummaryDto summary
) {}

