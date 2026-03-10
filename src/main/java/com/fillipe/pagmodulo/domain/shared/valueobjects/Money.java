package com.fillipe.pagmodulo.domain.shared.valueobjects;

import com.fillipe.pagmodulo.domain.shared.exceptions.InvalidFieldException;

public record Money(int value, String currency) {

    public Money {
        if (value < 1) throw new InvalidFieldException("Money.value", "value tem que ser positivo");
        if (currency == null || currency.isBlank()) throw new InvalidFieldException("Money.currency", "currency não pode ser nulo ou em branco");
        if (currency.length() != 3) throw new InvalidFieldException("Money.currency", "o tamanho da codigo ISO de currency deve ser 3");
        if (!currency.equals("BRL")) throw new InvalidFieldException("Money.currency", "currency deve ser BRL");
    }
}
