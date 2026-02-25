package com.fillipe.pagmodulo.infrastructure.pagbank.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PagBankItemDto(
        @JsonProperty("reference_id") String referenceId,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("quantity") Integer quantity,
        @JsonProperty("unit_amount") Integer unitAmount,
        @JsonProperty("image_url") String imageUrl
) {
}
