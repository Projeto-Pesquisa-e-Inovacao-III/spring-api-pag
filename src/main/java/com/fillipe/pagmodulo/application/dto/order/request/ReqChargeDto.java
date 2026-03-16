package com.fillipe.pagmodulo.application.dto.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record ReqChargeDto(
        @NotBlank(message = "O ID da cobrança é obrigatório")
        String id,

        @JsonProperty("reference_id")
        String referenceId,

        @NotBlank(message = "O status da cobrança é obrigatório")
        String status,

        @JsonProperty("created_at")
        OffsetDateTime createdAt,

        @JsonProperty("paid_at")
        OffsetDateTime paidAt,

        String description,

        @NotNull(message = "O valor da cobrança é obrigatório")
        @Valid
        ReqAmountDto amount,

        @JsonProperty("payment_response")
        @Valid
        ReqPaymentResponseDto paymentResponse,

        @JsonProperty("payment_method")
        @Valid
        ReqPaymentMethodOrderDto paymentMethod
) {}

