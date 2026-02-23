package com.fillipe.pagmodulo.domain.valueobject;

public record Customer(
        String externalCustomerId,
        String name,
        String email,
        TaxDocument taxDocument,
        Phone phone
) {}
