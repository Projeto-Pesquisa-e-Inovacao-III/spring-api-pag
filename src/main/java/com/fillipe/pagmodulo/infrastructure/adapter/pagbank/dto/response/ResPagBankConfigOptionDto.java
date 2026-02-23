package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResPagBankConfigOptionDto(

    @JsonProperty("option")
    String option,

    @JsonProperty("value")
    String value

) {}

