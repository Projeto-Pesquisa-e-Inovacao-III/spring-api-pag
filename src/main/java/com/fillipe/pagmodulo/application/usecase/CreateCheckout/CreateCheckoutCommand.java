package com.fillipe.pagmodulo.application.usecase.CreateCheckout;

import com.fillipe.pagmodulo.domain.checkout.valueobject.Customer;
import com.fillipe.pagmodulo.domain.checkout.valueobject.Item;
import com.fillipe.pagmodulo.domain.checkout.valueobject.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

public record CreateCheckoutCommand(
        LocalDateTime expirationDate,
        Customer customer,
        List<Item> items,
        Integer additionalAmount,
        Integer discountAmount,
        List<PaymentMethod> paymentMethods,
        String softDescriptor,
        String redirectUrl,
        String returnUrl,
        List<String> notificationUrls,
        List<String> paymentNotificationUrls
) {

    public CreateCheckoutCommand {
        if (expirationDate == null) {
            throw new IllegalArgumentException("expirationDate não pode ser nulo");
        }
        if (customer == null) {
            throw new IllegalArgumentException("customer não pode ser nulo");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("items não pode ser vazio");
        }
    }
}

