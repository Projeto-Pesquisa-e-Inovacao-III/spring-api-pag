package com.fillipe.pagmodulo.domain.checkout.valueobject;

public record Customer(
        String externalCustomerId,
        String name,
        // Todo: email deve virar um valueobject e ter validação
        String email,
        TaxDocument taxDocument,
        Phone phone
) {}
