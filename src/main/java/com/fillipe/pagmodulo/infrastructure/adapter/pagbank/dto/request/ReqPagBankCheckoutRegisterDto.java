package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReqPagBankCheckoutRegisterDto(
        @JsonProperty("reference_id") String referenceId,
        @JsonProperty("expiration_date") String expirationDate,
        @JsonProperty("customer") ReqPagBankCustomerDto customer,
        @JsonProperty("customer_modifiable") Boolean customerModifiable,
        @JsonProperty("items") ReqPagBankItemDto[] items,
        @JsonProperty("additional_amount") Integer additionalAmount,
        @JsonProperty("discount_amount") Integer discountAmount,
        @JsonProperty("payment_methods") ReqPagBankPaymentMethodDto[] paymentMethods,
        @JsonProperty("payment_methods_configs") ReqPagBankPaymentMethodConfigDto[] paymentMethodsConfigs,
        @JsonProperty("soft_descriptor") String softDescriptor,
        @JsonProperty("redirect_url") String redirectUrl,
        @JsonProperty("return_url") String returnUrl,
        @JsonProperty("notification_urls") String[] notificationUrls,
        @JsonProperty("payment_notification_urls") String[] paymentNotificationUrls
) {
    @Override
    public String toString() {
        return "ReqPagBankCheckoutRegisterDto{" +
                "referenceId='" + referenceId + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", customer=" + customer +
                ", customerModifiable=" + customerModifiable +
                ", items=" + Arrays.toString(items) +
                ", additionalAmount=" + additionalAmount +
                ", discountAmount=" + discountAmount +
                ", paymentMethods=" + Arrays.toString(paymentMethods) +
                ", paymentMethodsConfigs=" + Arrays.toString(paymentMethodsConfigs) +
                ", softDescriptor='" + softDescriptor + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", notificationUrls=" + Arrays.toString(notificationUrls) +
                ", paymentNotificationUrls=" + Arrays.toString(paymentNotificationUrls) +
                '}';
    }
}
