package com.fillipe.pagmodulo.infrastructure.persistence.mapper;

import com.fillipe.pagmodulo.domain.valueobject.PaymentConfigOption;
import com.fillipe.pagmodulo.domain.valueobject.PaymentMethod;
import com.fillipe.pagmodulo.infrastructure.persistence.embeddable.PaymentConfigOptionEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.PaymentMethodEntityJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMethodPersistenceMapper {

    PaymentMethodEntityJpa toEntity(PaymentMethod paymentMethod);

    PaymentMethod toDomain(PaymentMethodEntityJpa entity);

    // ---- PaymentConfigOption mappings ----
    PaymentConfigOptionEmbeddableJpa toEmbeddable(PaymentConfigOption option);

    PaymentConfigOption toDomainOption(PaymentConfigOptionEmbeddableJpa embeddable);
}

