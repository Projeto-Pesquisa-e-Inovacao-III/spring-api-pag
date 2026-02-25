package com.fillipe.pagmodulo.infrastructure.pagbank.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PagBankPhoneDto(
    @JsonProperty("country")
    String country,

    @JsonProperty("area")
    String area,

    @JsonProperty("number")
    String number
) {}

