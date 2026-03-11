package com.fillipe.pagmodulo.infrastructure.persistence.order.mapper;

import com.fillipe.pagmodulo.domain.order.valueobject.PaymentResponse;
import com.fillipe.pagmodulo.domain.order.valueobject.RawData;
import com.fillipe.pagmodulo.infrastructure.persistence.order.embeddable.PaymentResponseEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.order.embeddable.RawDataEmbeddableJpa;
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

