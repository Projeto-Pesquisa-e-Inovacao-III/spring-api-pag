package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReqPagBankPhoneDto(
        @JsonProperty("country") String country,
        @JsonProperty("area") String area,
        @JsonProperty("number") String number
) {
}
