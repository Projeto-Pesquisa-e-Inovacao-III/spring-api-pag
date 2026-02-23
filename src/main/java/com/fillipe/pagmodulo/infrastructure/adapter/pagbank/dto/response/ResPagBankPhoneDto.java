package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResPagBankPhoneDto (
    @JsonProperty("country")
    String country,

    @JsonProperty("area")
    String area,

    @JsonProperty("number")
    String number
) {}

