package com.csf.api_pag.infrastructure.pagbank.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.csf.api_pag.infrastructure.pagbank.dto.common.PagBankCustomerDto;
import com.csf.api_pag.infrastructure.pagbank.dto.common.PagBankItemDto;

import java.util.List;

public record ResPagBankPaymentUpdateCheckoutDto(
        @JsonProperty("id") String id,
        @JsonProperty("reference_id") String referenceId,
        @JsonProperty("expiration_date") String expirationDate,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("status") String status,
        @JsonProperty("customer") PagBankCustomerDto customer,
        @JsonProperty("customer_modifiable") Boolean customerModifiable,
        @JsonProperty("items") List<PagBankItemDto> items,
        @JsonProperty("additional_amount") Integer additionalAmount,
        @JsonProperty("discount_amount") Integer discountAmount,
        @JsonProperty("redirect_url") String redirectUrl,
        @JsonProperty("return_url") String returnUrl,
        @JsonProperty("notification_urls") List<String> notificationUrls
) {}

