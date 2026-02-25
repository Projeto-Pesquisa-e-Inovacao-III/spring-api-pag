package com.fillipe.pagmodulo.infrastructure.pagbank.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PagBankPaymentMethodDto(
        @JsonProperty("type") String type
) {
}
