package com.csf.pagmodulo.application.dto.order.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReqSummaryDto(
        @NotNull(message = "O total é obrigatório")
        @Min(value = 0, message = "O total não pode ser negativo")
        Integer total,

        @NotNull(message = "O valor pago é obrigatório")
        @Min(value = 0, message = "O valor pago não pode ser negativo")
        Integer paid,

        @NotNull(message = "O valor estornado é obrigatório")
        @Min(value = 0, message = "O valor estornado não pode ser negativo")
        Integer refunded
) {}

