package com.csf.pagmodulo.presentation.dto.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ReqCustomerOrderDto(
        @NotBlank(message = "O nome do cliente é obrigatório")
        String name,

        @NotBlank(message = "O email do cliente é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @JsonProperty("tax_id")
        @NotBlank(message = "O CPF/CNPJ é obrigatório")
        String taxId,

        @NotEmpty(message = "A lista de telefones é obrigatória")
        @Valid
        List<ReqPhoneOrderDto> phones
) {}

