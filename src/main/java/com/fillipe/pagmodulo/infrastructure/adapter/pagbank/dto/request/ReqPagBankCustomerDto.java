package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReqPagBankCustomerDto(
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("tax_id") String taxId,
        @JsonProperty("phone") ReqPagBankPhoneDto phone
) {
}
