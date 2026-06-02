package com.csf.api_pag.infrastructure.pagbank.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.csf.api_pag.infrastructure.pagbank.dto.common.PagBankCustomerDto;
import com.csf.api_pag.infrastructure.pagbank.dto.common.PagBankItemDto;
import com.csf.api_pag.infrastructure.pagbank.dto.common.PagBankPaymentMethodConfigDto;
import com.csf.api_pag.infrastructure.pagbank.dto.common.PagBankPaymentMethodDto;

import java.util.List;

public record ResPagBankCheckoutDto(
        @JsonProperty("id") String id,
        @JsonProperty("reference_id") String referenceId,
        @JsonProperty("expiration_date") String expirationDate,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("status") String status,
        @JsonProperty("customer") PagBankCustomerDto customer,
        @JsonProperty("items") List<PagBankItemDto> items,
        @JsonProperty("additional_amount") Integer additionalAmount,
        @JsonProperty("discount_amount") Integer discountAmount,
        @JsonProperty("payment_methods") List<PagBankPaymentMethodDto> paymentMethods,
        @JsonProperty("payment_methods_configs") List<PagBankPaymentMethodConfigDto> paymentMethodsConfigs
) {
    @Override
    public String toString() {
        return "ResPagBankCheckoutDto{" +
                "id='" + id + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", status='" + status + '\'' +
                ", customer=" + customer +
                ", items=" + items +
                ", additionalAmount=" + additionalAmount +
                ", discountAmount=" + discountAmount +
                ", paymentMethods=" + paymentMethods +
                ", paymentMethodsConfigs=" + paymentMethodsConfigs +
                '}';
    }
}

