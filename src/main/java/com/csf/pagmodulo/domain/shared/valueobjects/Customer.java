package com.csf.pagmodulo.domain.shared.valueobjects;

public record Customer(
        String externalCustomerId,
        String name,
        // Todo: email deve virar um valueobject e ter validação
        String email,
        TaxDocument taxDocument,
        Phone phone
) {

    public Customer withExternalCustomerId(String externalCustomerId) {
        return new Customer(externalCustomerId, name, email, taxDocument, phone);
    }
}
