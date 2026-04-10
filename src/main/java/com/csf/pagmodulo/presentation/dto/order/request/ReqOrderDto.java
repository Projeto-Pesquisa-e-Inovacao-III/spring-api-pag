package com.csf.pagmodulo.presentation.dto.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.List;

public record ReqOrderDto(
        @NotBlank(message = "O ID do pedido é obrigatório")
        String id,

        @JsonProperty("reference_id")
        String referenceId,

        @JsonProperty("created_at")
        OffsetDateTime createdAt,

        @NotEmpty(message = "A lista de itens é obrigatória e não pode estar vazia")
        @Valid
        List<ReqItemOrderDto> items,

        @NotNull(message = "O cliente é obrigatório")
        @Valid
        ReqCustomerOrderDto customer,

        @NotEmpty(message = "A lista de cobranças é obrigatória e não pode estar vazia")
        @Valid
        List<ReqChargeDto> charges
) {}



