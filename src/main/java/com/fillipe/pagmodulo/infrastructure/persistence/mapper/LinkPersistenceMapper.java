package com.fillipe.pagmodulo.infrastructure.persistence.mapper;

import com.fillipe.pagmodulo.domain.valueobject.Link;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.LinkEntityJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LinkPersistenceMapper {

    @Mapping(target = "id", ignore = true)
    LinkEntityJpa toEntity(Link link);

    Link toDomain(LinkEntityJpa entity);
}

