package com.fillipe.pagmodulo.infrastructure.persistence.mapper;

import com.fillipe.pagmodulo.domain.valueobject.Customer;
import com.fillipe.pagmodulo.domain.valueobject.Phone;
import com.fillipe.pagmodulo.domain.valueobject.TaxDocument;
import com.fillipe.pagmodulo.infrastructure.persistence.embeddable.PhoneEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.embeddable.TaxDocumentEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.CustomerEntityJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {

    CustomerEntityJpa toEntity(Customer customer);

    Customer toDomain(CustomerEntityJpa entity);

    // ---- Phone mappings ----

    PhoneEmbeddableJpa toPhoneEmbeddable(Phone phone);

    Phone toDomainPhone(PhoneEmbeddableJpa embeddable);

    // ---- TaxDocument mappings ----

    TaxDocumentEmbeddableJpa toTaxDocumentEmbeddable(TaxDocument taxDocument);

    TaxDocument toDomainTaxDocument(TaxDocumentEmbeddableJpa embeddable);
}

