package com.csf.pagmodulo.presentation.dto.checkout.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReqUpdateCheckoutDto(
    @JsonProperty("id") String id,
    @JsonProperty("reference_id") String referenceId,
    @JsonProperty("status") String status
) {}

