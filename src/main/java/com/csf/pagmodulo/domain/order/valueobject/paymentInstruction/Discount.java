package com.csf.pagmodulo.domain.order.valueobject.paymentInstruction;

import com.csf.pagmodulo.domain.shared.exceptions.InvalidFieldException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public record Discount(String date, int value) {

    public Discount {
        if (date == null || date.isBlank())
            throw new InvalidFieldException("Discount.date", "date não pode ser nulo ou em branco");
        if (date.length() != 10)
            throw new InvalidFieldException("Discount.date", "date deve ter 10 caracteres no formato YYYY-MM-DD");
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidFieldException("Discount.date", "date deve estar no formato YYYY-MM-DD");
        }
        if (value <= 0)
            throw new InvalidFieldException("Discount.value", "value deve ser um inteiro positivo (ex: 2% = 200)");
    }
}

