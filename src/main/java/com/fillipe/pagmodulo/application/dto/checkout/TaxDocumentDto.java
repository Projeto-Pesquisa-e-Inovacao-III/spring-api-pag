package com.fillipe.pagmodulo.application.dto.checkout;

import com.fillipe.pagmodulo.domain.shared.valueobjects.TaxDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaxDocumentDto(
        @NotBlank(message = "O valor do documento é obrigatório")
        String value,

        @NotNull(message = "O tipo do documento é obrigatório")
        TaxDocumentType type
) {}

