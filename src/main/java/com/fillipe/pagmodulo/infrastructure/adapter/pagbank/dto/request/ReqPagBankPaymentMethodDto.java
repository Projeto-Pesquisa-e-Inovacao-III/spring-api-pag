package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReqPagBankPaymentMethodDto(
        @JsonProperty("type") String type
) {
}
