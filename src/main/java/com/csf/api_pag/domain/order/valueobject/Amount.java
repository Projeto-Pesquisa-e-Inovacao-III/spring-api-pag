package com.csf.api_pag.domain.order.valueobject;

import com.csf.api_pag.domain.shared.exceptions.InvalidFieldException;
import com.csf.api_pag.domain.shared.valueobjects.Money;

public record Amount(Money money, Summary summary) {

    public Amount {
        if (money == null) throw new InvalidFieldException("Amount.money", "money não pode ser nulo");
        if (summary == null) throw new InvalidFieldException("Amount.summary", "summary não pode ser nulo");
    }
}
