package com.csf.api_pag.infrastructure.pagbank.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PagBankPaymentMethodConfigDto(

    @JsonProperty("type")
    String type,

    @JsonProperty("config_options")
    List<PagBankConfigOptionDto> configOptions

) {}

