package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResPagBankLinkDto(

        @JsonProperty("rel")
        String rel,

        @JsonProperty("href")
        String href,

        @JsonProperty("method")
        String method
) {}

