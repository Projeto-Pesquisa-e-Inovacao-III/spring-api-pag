package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ResPagBankPaymentMethodDto(

    @JsonProperty("type")
    String type,

    @JsonProperty("brands")
    List<String> brands
) {}

