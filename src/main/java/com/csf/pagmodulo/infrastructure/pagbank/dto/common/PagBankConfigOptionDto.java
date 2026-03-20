package com.csf.pagmodulo.infrastructure.pagbank.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PagBankConfigOptionDto(
        @JsonProperty("option") String option,
        @JsonProperty("value") String value
) {
}
