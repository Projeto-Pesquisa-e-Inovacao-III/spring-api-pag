package com.fillipe.pagmodulo.infrastructure.persistence.mapper;

import com.fillipe.pagmodulo.domain.valueobject.Item;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.ItemEntityJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemPersistenceMapper {

    ItemEntityJpa toEntity(Item item);

    Item toDomain(ItemEntityJpa entity);
}

