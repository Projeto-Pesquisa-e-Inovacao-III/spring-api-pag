package com.csf.api_pag.infrastructure.persistence.order.mapper;

import com.csf.api_pag.domain.shared.valueobjects.Item;
import com.csf.api_pag.infrastructure.persistence.order.entity.OrderEntityJpa;
import com.csf.api_pag.infrastructure.persistence.order.entity.OrderItemEntityJpa;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemPersistenceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", expression = "java(order)")
    OrderItemEntityJpa toEntity(Item domain, @Context OrderEntityJpa order);

    List<OrderItemEntityJpa> toEntityList(List<Item> domainList, @Context OrderEntityJpa order);

    Item toDomain(OrderItemEntityJpa entity);

    List<Item> toDomainList(List<OrderItemEntityJpa> entityList);
}

