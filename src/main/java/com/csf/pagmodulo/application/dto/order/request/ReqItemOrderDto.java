package com.csf.pagmodulo.application.dto.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReqItemOrderDto(
        @JsonProperty("reference_id")
        @NotBlank(message = "O reference_id do item é obrigatório")
        String referenceId,

        @NotBlank(message = "O nome do item é obrigatório")
        @Size(max = 100, message = "O nome do item deve ter no máximo 100 caracteres")
        String name,

        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
        Integer quantity,

        @JsonProperty("unit_amount")
        @NotNull(message = "O valor unitário é obrigatório")
        @Min(value = 1, message = "O valor unitário deve ser no mínimo 1")
        Integer unitAmount
) {}

