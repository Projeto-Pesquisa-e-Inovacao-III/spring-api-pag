package com.csf.api_pag.infrastructure.pagbank.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PagBankConfigOptionDto(
        @JsonProperty("option") String option,
        @JsonProperty("value") String value
) {
}
