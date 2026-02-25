package com.fillipe.pagmodulo.infrastructure.pagbank.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.common.PagBankCustomerDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.common.PagBankItemDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.common.PagBankPaymentMethodConfigDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.common.PagBankPaymentMethodDto;

import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReqPagBankCheckoutDto(
        @JsonProperty("reference_id") String referenceId,
        @JsonProperty("expiration_date") String expirationDate,
        @JsonProperty("customer") PagBankCustomerDto customer,
        @JsonProperty("customer_modifiable") Boolean customerModifiable,
        @JsonProperty("items") List<PagBankItemDto> items,
        @JsonProperty("additional_amount") Integer additionalAmount,
        @JsonProperty("discount_amount") Integer discountAmount,
        @JsonProperty("payment_methods") List<PagBankPaymentMethodDto> paymentMethods,
        @JsonProperty("payment_methods_configs") List<PagBankPaymentMethodConfigDto> paymentMethodsConfigs,
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
                ", items=" + items.toString() +
                ", additionalAmount=" + additionalAmount +
                ", discountAmount=" + discountAmount +
                ", paymentMethods=" + paymentMethods.toString() +
                ", paymentMethodsConfigs=" + paymentMethodsConfigs.toString() +
                ", softDescriptor='" + softDescriptor + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", notificationUrls=" + Arrays.toString(notificationUrls) +
                ", paymentNotificationUrls=" + Arrays.toString(paymentNotificationUrls) +
                '}';
    }
}
