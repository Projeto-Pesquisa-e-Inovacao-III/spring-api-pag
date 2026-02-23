package com.fillipe.pagmodulo.domain.valueobject;

public record Customer(
        Long id,
        String name,
        String email,
        TaxDocument taxDocument,
        Phone phone
) {}
