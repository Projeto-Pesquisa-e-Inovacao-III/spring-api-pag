package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ResPagBankCheckoutRegisterDto(
        @JsonProperty("id") String id,
        @JsonProperty("reference_id") String referenceId,
        @JsonProperty("expiration_date") String expirationDate,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("status") String status,
        @JsonProperty("customer") ResPagBankCustomerDto customer,
        @JsonProperty("customer_modifiable") Boolean customerModifiable,
        @JsonProperty("items") List<ResPagBankItemDto> items,
        @JsonProperty("additional_amount") Integer additionalAmount,
        @JsonProperty("discount_amount") Integer discountAmount,
        @JsonProperty("payment_methods") List<ResPagBankPaymentMethodDto> paymentMethods,
        @JsonProperty("payment_methods_configs") List<ResPagBankPaymentMethodConfigDto> paymentMethodsConfigs,
        @JsonProperty("soft_descriptor") String softDescriptor,
        @JsonProperty("redirect_url") String redirectUrl,
        @JsonProperty("return_url") String returnUrl,
        @JsonProperty("notification_urls") List<String> notificationUrls,
        @JsonProperty("payment_notification_urls") List<String> paymentNotificationUrls,
        @JsonProperty("links") List<ResPagBankLinkDto> links
) {
    @Override
    public String toString() {
        return "ResPagBankCheckoutRegisterDto{" +
                "id='" + id + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", status='" + status + '\'' +
                ", customer=" + customer +
                ", customerModifiable=" + customerModifiable +
                ", items=" + items +
                ", additionalAmount=" + additionalAmount +
                ", discountAmount=" + discountAmount +
                ", paymentMethods=" + paymentMethods +
                ", paymentMethodsConfigs=" + paymentMethodsConfigs +
                ", softDescriptor='" + softDescriptor + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", notificationUrls=" + notificationUrls +
                ", paymentNotificationUrls=" + paymentNotificationUrls +
                ", links=" + links +
                '}';
    }
}

