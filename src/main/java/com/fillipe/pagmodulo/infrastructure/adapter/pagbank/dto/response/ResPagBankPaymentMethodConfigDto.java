package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ResPagBankPaymentMethodConfigDto (

    @JsonProperty("type")
    String type,

    @JsonProperty("config_options")
    List<ResPagBankConfigOptionDto> configOptions

) {}

