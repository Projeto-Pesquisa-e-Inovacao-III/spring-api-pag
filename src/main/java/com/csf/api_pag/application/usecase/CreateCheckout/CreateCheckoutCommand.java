package com.csf.api_pag.application.usecase.CreateCheckout;

import com.csf.api_pag.domain.shared.valueobjects.Customer;
import com.csf.api_pag.domain.shared.valueobjects.Item;

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

