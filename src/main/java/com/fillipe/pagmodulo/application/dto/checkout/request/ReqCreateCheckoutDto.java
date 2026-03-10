package com.fillipe.pagmodulo.application.dto.checkout.request;

import com.fillipe.pagmodulo.application.dto.checkout.CustomerDto;
import com.fillipe.pagmodulo.application.dto.checkout.ItemDto;
import com.fillipe.pagmodulo.application.dto.checkout.PaymentMethodDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReqCreateCheckoutDto(
        @NotNull(message = "O cliente é obrigatório")
        @Valid
        CustomerDto customer,

        @NotEmpty(message = "A lista de itens é obrigatória e não pode estar vazia")
        @Valid
        List<ItemDto> items,

        @NotEmpty(message = "A lista de métodos de pagamento é obrigatória e não pode estar vazia")
        @Valid
        List<PaymentMethodDto> paymentMethods,

        String softDescriptor,
        String redirectUrl,
        String returnUrl,
        List<String> notificationUrls,
        List<String> paymentNotificationUrls,
        Integer additionalAmount,
        Integer discountAmount
) {}

