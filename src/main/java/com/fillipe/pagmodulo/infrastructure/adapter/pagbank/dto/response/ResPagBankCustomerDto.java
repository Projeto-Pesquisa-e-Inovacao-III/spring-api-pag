package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResPagBankCustomerDto(

    @JsonProperty("name")
    String name,

    @JsonProperty("email")
    String email,

    @JsonProperty("tax_id")
    String taxId,

    @JsonProperty("phone")
    ResPagBankPhoneDto phone
) {}

