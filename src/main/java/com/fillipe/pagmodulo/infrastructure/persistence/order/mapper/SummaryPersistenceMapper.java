package com.fillipe.pagmodulo.infrastructure.persistence.order.mapper;

import com.fillipe.pagmodulo.domain.order.valueobject.Summary;
import com.fillipe.pagmodulo.infrastructure.persistence.order.embeddable.SummaryEmbeddableJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SummaryPersistenceMapper {

    SummaryEmbeddableJpa toEmbeddable(Summary summary);

    Summary toDomain(SummaryEmbeddableJpa embeddable);
}

