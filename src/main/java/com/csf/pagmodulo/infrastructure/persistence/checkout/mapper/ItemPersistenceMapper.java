package com.csf.pagmodulo.infrastructure.persistence.checkout.mapper;

import com.csf.pagmodulo.domain.shared.valueobjects.Item;
import com.csf.pagmodulo.infrastructure.persistence.checkout.entity.CheckoutEntityJpa;
import com.csf.pagmodulo.infrastructure.persistence.checkout.entity.CheckoutItemEntityJpa;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ItemPersistenceMapper {

    CheckoutItemEntityJpa toEntity(Item item, @Context CheckoutEntityJpa checkout);

    @AfterMapping
    default void setCheckout(@MappingTarget CheckoutItemEntityJpa entity, @Context CheckoutEntityJpa checkout) {
        entity.setCheckout(checkout);
    }

    default List<CheckoutItemEntityJpa> toEntityList(List<Item> items, CheckoutEntityJpa checkout) {
        if (items == null) return List.of();
        return items.stream()
                .map(item -> toEntity(item, checkout))
                .collect(Collectors.toList());
    }

    Item toDomain(CheckoutItemEntityJpa entity);



}

