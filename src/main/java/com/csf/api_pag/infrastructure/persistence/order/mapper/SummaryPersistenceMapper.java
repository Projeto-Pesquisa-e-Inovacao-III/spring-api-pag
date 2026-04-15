package com.csf.api_pag.infrastructure.persistence.order.mapper;

import com.csf.api_pag.domain.order.valueobject.Summary;
import com.csf.api_pag.infrastructure.persistence.order.embeddable.SummaryEmbeddableJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SummaryPersistenceMapper {

    SummaryEmbeddableJpa toEmbeddable(Summary summary);

    Summary toDomain(SummaryEmbeddableJpa embeddable);
}

