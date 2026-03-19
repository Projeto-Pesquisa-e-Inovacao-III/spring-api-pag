package com.fillipe.pagmodulo.application.usecase.CreateCheckout;

import com.fillipe.pagmodulo.domain.shared.valueobjects.Customer;
import com.fillipe.pagmodulo.domain.shared.valueobjects.Item;

import java.util.List;

public record CreateCheckoutCommand(
        Customer customer,
        List<Item> items,
        Integer additionalAmount,
        Integer discountAmount
) {

    public CreateCheckoutCommand {
        if (customer == null) {
            throw new IllegalArgumentException("customer não pode ser nulo");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("items não pode ser vazio");
        }
    }
}

