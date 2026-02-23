package com.fillipe.pagmodulo.infrastructure.persistence.mapper;

import com.fillipe.pagmodulo.domain.valueobject.Item;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.CheckoutEntityJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.ItemEntityJpa;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ItemPersistenceMapper {

    ItemEntityJpa toEntity(Item item, @Context CheckoutEntityJpa checkout);

    @AfterMapping
    default void setCheckout(@MappingTarget ItemEntityJpa entity, @Context CheckoutEntityJpa checkout) {
        entity.setCheckout(checkout);
    }

    default List<ItemEntityJpa> toEntityList(List<Item> items, CheckoutEntityJpa checkout) {
        if (items == null) return List.of();
        return items.stream()
                .map(item -> toEntity(item, checkout))
                .collect(Collectors.toList());
    }

    Item toDomain(ItemEntityJpa entity);



}

