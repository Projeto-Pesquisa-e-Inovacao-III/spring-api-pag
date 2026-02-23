package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReqPagBankConfigOptionDto(
        @JsonProperty("option") String option,
        @JsonProperty("value") String value
) {
}
