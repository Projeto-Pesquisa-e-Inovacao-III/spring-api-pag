package com.csf.api_pag.presentation.dto.shared;

import com.csf.api_pag.domain.shared.valueobjects.TaxDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaxDocumentDto(
        @NotBlank(message = "O valor do documento é obrigatório")
        String value,

        @NotNull(message = "O tipo do documento é obrigatório")
        TaxDocumentType type
) {}

