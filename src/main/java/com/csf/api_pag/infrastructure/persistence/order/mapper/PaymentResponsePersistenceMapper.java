package com.csf.api_pag.infrastructure.persistence.order.mapper;

import com.csf.api_pag.domain.order.valueobject.PaymentResponse;
import com.csf.api_pag.domain.order.valueobject.RawData;
import com.csf.api_pag.infrastructure.persistence.order.embeddable.PaymentResponseEmbeddableJpa;
import com.csf.api_pag.infrastructure.persistence.order.embeddable.RawDataEmbeddableJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentResponsePersistenceMapper {

    // ---- PaymentResponse ----

    PaymentResponseEmbeddableJpa toEmbeddable(PaymentResponse paymentResponse);

    PaymentResponse toDomain(PaymentResponseEmbeddableJpa embeddable);

    // ---- RawData ----

    RawDataEmbeddableJpa toRawDataEmbeddable(RawData rawData);

    RawData toRawDataDomain(RawDataEmbeddableJpa embeddable);
}

