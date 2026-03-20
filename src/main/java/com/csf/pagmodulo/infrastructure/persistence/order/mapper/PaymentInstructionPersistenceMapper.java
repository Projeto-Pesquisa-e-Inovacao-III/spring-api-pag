package com.csf.pagmodulo.infrastructure.persistence.order.mapper;

import com.csf.pagmodulo.domain.order.valueobject.paymentInstruction.Discount;
import com.csf.pagmodulo.domain.order.valueobject.paymentInstruction.Fine;
import com.csf.pagmodulo.domain.order.valueobject.paymentInstruction.Interest;
import com.csf.pagmodulo.domain.order.valueobject.paymentInstruction.PaymentInstruction;
import com.csf.pagmodulo.infrastructure.persistence.order.embeddable.DiscountEmbeddableJpa;
import com.csf.pagmodulo.infrastructure.persistence.order.embeddable.FineEmbeddableJpa;
import com.csf.pagmodulo.infrastructure.persistence.order.embeddable.InterestEmbeddableJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentInstructionPersistenceMapper {

    // ---- Fine ----

    FineEmbeddableJpa toFineEmbeddable(Fine fine);

    Fine toFineDomain(FineEmbeddableJpa embeddable);

    // ---- Interest ----

    InterestEmbeddableJpa toInterestEmbeddable(Interest interest);

    Interest toInterestDomain(InterestEmbeddableJpa embeddable);

    // ---- Discount ----

    DiscountEmbeddableJpa toDiscountEmbeddable(Discount discount);

    Discount toDiscountDomain(DiscountEmbeddableJpa embeddable);

    List<DiscountEmbeddableJpa> toDiscountEmbeddableList(List<Discount> discounts);

    List<Discount> toDiscountDomainList(List<DiscountEmbeddableJpa> embeddables);

    // ---- PaymentInstruction (assembled from fine + interest + discounts) ----

    @Mapping(target = "fine",      source = "fine")
    @Mapping(target = "interest",  source = "interest")
    @Mapping(target = "discounts", source = "discounts")
    PaymentInstruction toDomain(FineEmbeddableJpa fine, InterestEmbeddableJpa interest, List<DiscountEmbeddableJpa> discounts);
}

