package com.fillipe.pagmodulo.infrastructure.persistence.shared.mapper;

import com.fillipe.pagmodulo.domain.shared.valueobjects.Customer;
import com.fillipe.pagmodulo.domain.shared.valueobjects.Phone;
import com.fillipe.pagmodulo.domain.shared.valueobjects.TaxDocument;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.embeddable.PhoneEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.embeddable.TaxDocumentEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.shared.embeddable.CustomerEmbeddableJpa;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {

    CustomerEmbeddableJpa toEmbeddable(Customer customer);

    Customer toDomain(CustomerEmbeddableJpa embeddable);

    // ---- Phone mappings ----

    PhoneEmbeddableJpa toPhoneEmbeddable(Phone phone);

    Phone toDomainPhone(PhoneEmbeddableJpa embeddable);

    // ---- TaxDocument mappings ----

    TaxDocumentEmbeddableJpa toTaxDocumentEmbeddable(TaxDocument taxDocument);

    TaxDocument toDomainTaxDocument(TaxDocumentEmbeddableJpa embeddable);
}
