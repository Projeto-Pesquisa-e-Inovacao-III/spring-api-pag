package com.csf.api_pag.infrastructure.pagbank.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PagBankPaymentMethodDto(
        @JsonProperty("type") String type
) {
}
