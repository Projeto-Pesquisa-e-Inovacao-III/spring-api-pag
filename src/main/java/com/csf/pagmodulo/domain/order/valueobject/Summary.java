package com.csf.pagmodulo.domain.order.valueobject;

import com.csf.pagmodulo.domain.shared.exceptions.InvalidFieldException;

public record Summary(int total, int paid, int refunded) {

    public Summary {
        if (total < 0) throw new InvalidFieldException("Summary.total", "total não pode ser negativo");
        if (paid < 0) throw new InvalidFieldException("Summary.paid", "paid não pode ser negativo");
        if (refunded < 0) throw new InvalidFieldException("Summary.refunded", "refunded não pode ser negativo");
    }
}

