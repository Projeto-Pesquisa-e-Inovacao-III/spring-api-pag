package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResPagBankItemDto(

    @JsonProperty("reference_id")
    String referenceId,

    @JsonProperty("name")
    String name,

    @JsonProperty("quantity")
    Integer quantity,

    @JsonProperty("unit_amount")
    Integer unitAmount,

    @JsonProperty("image_url")
    String imageUrl,
    
    @JsonProperty("description")
    String description
){}
