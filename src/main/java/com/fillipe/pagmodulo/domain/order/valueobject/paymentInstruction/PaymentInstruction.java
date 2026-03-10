package com.fillipe.pagmodulo.domain.order.valueobject.paymentInstruction;

import com.fillipe.pagmodulo.domain.shared.exceptions.InvalidFieldException;

import java.util.List;

public record PaymentInstruction(Fine fine, Interest interest, List<Discount> discounts) {

    public PaymentInstruction {
        if (fine == null)
            throw new InvalidFieldException("PaymentInstruction.fine", "fine não pode ser nulo");
        if (interest == null)
            throw new InvalidFieldException("PaymentInstruction.interest", "interest não pode ser nulo");
        if (discounts == null || discounts.isEmpty())
            throw new InvalidFieldException("PaymentInstruction.discounts", "discounts não pode ser nulo ou vazio");
        discounts.forEach(d -> {
            if (d == null)
                throw new InvalidFieldException("PaymentInstruction.discounts", "nenhum desconto pode ser nulo");
        });
    }
}

