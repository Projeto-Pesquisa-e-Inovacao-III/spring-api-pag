package com.csf.pagmodulo.infrastructure.pagbank.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResPagBankErrorDto(
        @JsonProperty("error_messages")
        List<ErrorDetail> errorMessages
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ErrorDetail(
            String error,
            String description,
            @JsonProperty("parameter_name")
            String parameterName
    ) {}
}
