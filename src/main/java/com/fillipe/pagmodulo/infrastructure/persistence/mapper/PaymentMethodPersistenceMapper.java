package com.fillipe.pagmodulo.infrastructure.persistence.mapper;

import com.fillipe.pagmodulo.domain.valueobject.PaymentConfigOption;
import com.fillipe.pagmodulo.domain.valueobject.PaymentMethod;
import com.fillipe.pagmodulo.infrastructure.persistence.embeddable.PaymentConfigOptionEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.CheckoutEntityJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.PaymentMethodEntityJpa;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PaymentMethodPersistenceMapper {

    @Mapping(target = "checkout", ignore = true)
    PaymentMethodEntityJpa toEntity(PaymentMethod paymentMethod, @Context CheckoutEntityJpa checkout);

    PaymentMethod toDomain(PaymentMethodEntityJpa entity);

    @AfterMapping
    default void setCheckout(@MappingTarget PaymentMethodEntityJpa entity, @Context CheckoutEntityJpa checkout) {
        entity.setCheckout(checkout);
    }

    default List<PaymentMethodEntityJpa> toEntityList(List<PaymentMethod> paymentMethods, CheckoutEntityJpa checkout) {
        if (paymentMethods == null) return List.of();
        return paymentMethods.stream()
                .map(pm -> toEntity(pm, checkout))
                .collect(Collectors.toList());
    }

    // ---- PaymentConfigOption mappings ----
    PaymentConfigOptionEmbeddableJpa toEmbeddable(PaymentConfigOption option);

    PaymentConfigOption toDomainOption(PaymentConfigOptionEmbeddableJpa embeddable);
}

