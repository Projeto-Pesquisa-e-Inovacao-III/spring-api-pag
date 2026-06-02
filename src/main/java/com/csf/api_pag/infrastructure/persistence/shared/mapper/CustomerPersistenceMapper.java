package com.csf.api_pag.infrastructure.persistence.shared.mapper;

import com.csf.api_pag.domain.shared.valueobjects.Customer;
import com.csf.api_pag.domain.shared.valueobjects.Phone;
import com.csf.api_pag.domain.shared.valueobjects.TaxDocument;
import com.csf.api_pag.infrastructure.persistence.shared.embeddable.PhoneEmbeddableJpa;
import com.csf.api_pag.infrastructure.persistence.shared.embeddable.TaxDocumentEmbeddableJpa;
import com.csf.api_pag.infrastructure.persistence.shared.embeddable.CustomerEmbeddableJpa;
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
