package com.fillipe.pagmodulo.infrastructure.persistence.checkout.mapper;

import com.fillipe.pagmodulo.domain.shared.valueobjects.Customer;
import com.fillipe.pagmodulo.domain.shared.valueobjects.Phone;
import com.fillipe.pagmodulo.domain.shared.valueobjects.TaxDocument;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.embeddable.PhoneEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.embeddable.TaxDocumentEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.entity.CheckoutEntityJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.entity.CustomerEntityJpa;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {

    @Mapping(source = "externalCustomerId", target = "id")
    CustomerEntityJpa toEntity(Customer customer, @Context CheckoutEntityJpa checkout);

    @AfterMapping
    default void setCheckout(@MappingTarget CustomerEntityJpa entity, @Context CheckoutEntityJpa checkout) {
        entity.setCheckout(checkout);
    }

    @Mapping(source = "id", target = "externalCustomerId")
    Customer toDomain(CustomerEntityJpa entity);

    // ---- Phone mappings ----

    PhoneEmbeddableJpa toPhoneEmbeddable(Phone phone);

    Phone toDomainPhone(PhoneEmbeddableJpa embeddable);

    // ---- TaxDocument mappings ----

    TaxDocumentEmbeddableJpa toTaxDocumentEmbeddable(TaxDocument taxDocument);

    TaxDocument toDomainTaxDocument(TaxDocumentEmbeddableJpa embeddable);
}

