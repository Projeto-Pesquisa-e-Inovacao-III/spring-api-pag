package com.fillipe.pagmodulo.infrastructure.pagbank.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PagBankCustomerDto(

    @JsonProperty("name")
    String name,

    @JsonProperty("email")
    String email,

    @JsonProperty("tax_id")
    String taxId,

    @JsonProperty("phone")
    PagBankPhoneDto phone
) {}