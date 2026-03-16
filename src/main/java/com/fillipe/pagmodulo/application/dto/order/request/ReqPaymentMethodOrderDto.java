package com.fillipe.pagmodulo.application.dto.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record ReqPaymentMethodOrderDto(
        @NotBlank(message = "O tipo do método de pagamento é obrigatório")
        String type,

        ReqHolderDto holder
) {
    public record ReqHolderDto(
            String name,

            @JsonProperty("tax_id")
            String taxId
    ) {}
}

