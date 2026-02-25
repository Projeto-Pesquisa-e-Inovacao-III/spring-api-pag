package com.fillipe.pagmodulo.infrastructure.persistence.mapper;

import com.fillipe.pagmodulo.domain.checkout.valueobject.Link;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.CheckoutEntityJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.LinkEntityJpa;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LinkPersistenceMapper {

    @Mapping(target = "checkout", ignore = true)
    LinkEntityJpa toEntity(Link link, @Context CheckoutEntityJpa checkout);

    @AfterMapping
    default void setCheckout(@MappingTarget LinkEntityJpa entity, @Context CheckoutEntityJpa checkout) {
        entity.setCheckout(checkout);
    }

    default List<LinkEntityJpa> toEntityList(List<Link> links, CheckoutEntityJpa checkout) {
        if (links == null) return List.of();
        return links.stream()
                .map(link -> toEntity(link, checkout))
                .collect(Collectors.toList());
    }

    Link toDomain(LinkEntityJpa entity);
}

