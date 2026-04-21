package com.csf.api_pag.domain.order.valueobject.paymentInstruction;

import com.csf.api_pag.domain.shared.exceptions.InvalidFieldException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public record Fine(String date, int value) {

    public Fine {
        if (date == null || date.isBlank())
            throw new InvalidFieldException("Fine.date", "date não pode ser nulo ou em branco");
        if (date.length() != 10)
            throw new InvalidFieldException("Fine.date", "date deve ter 10 caracteres no formato YYYY-MM-DD");
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidFieldException("Fine.date", "date deve estar no formato YYYY-MM-DD");
        }
        if (value <= 0)
            throw new InvalidFieldException("Fine.value", "value deve ser um inteiro positivo (ex: 2% = 200)");
    }
}

