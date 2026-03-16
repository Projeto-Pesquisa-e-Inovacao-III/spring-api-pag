package com.fillipe.pagmodulo.application.dto.shared;

import jakarta.validation.constraints.*;

public record ItemDto(
        @NotBlank(message = "O ID externo do item é obrigatório")
        String externalItemId,

        @NotBlank(message = "O nome do item é obrigatório")
        @Size(max = 100, message = "O nome do item deve ter no máximo 100 caracteres")
        String name,

        @Size(max = 255, message = "A descrição do item deve ter no máximo 255 caracteres")
        String description,

        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
        @Max(value = 999, message = "A quantidade deve ser no máximo 999")
        Integer quantity,

        @NotNull(message = "O valor unitário é obrigatório")
        @Min(value = 1, message = "O valor unitário deve ser no mínimo 1")
        Integer unitAmount,

        String imageUrl
) {}

