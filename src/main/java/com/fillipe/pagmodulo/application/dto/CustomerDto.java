package com.fillipe.pagmodulo.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerDto(
        @NotBlank(message = "O ID externo do cliente é obrigatório")
        String externalCustomerId,

        @NotBlank(message = "O nome do cliente é obrigatório")
        String name,

        @NotBlank(message = "O email do cliente é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotNull(message = "O documento do cliente é obrigatório")
        @Valid
        TaxDocumentDto taxDocument,

        @NotNull(message = "O telefone do cliente é obrigatório")
        @Valid
        PhoneDto phone
) {}

