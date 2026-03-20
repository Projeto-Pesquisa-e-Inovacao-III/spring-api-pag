package com.csf.pagmodulo.infrastructure.persistence.order.mapper;

import com.csf.pagmodulo.domain.order.entity.Charge;
import com.csf.pagmodulo.domain.order.enums.BillingStatus;
import com.csf.pagmodulo.domain.order.valueobject.paymentInstruction.PaymentInstruction;
import com.csf.pagmodulo.domain.shared.valueobjects.ChargeId;
import com.csf.pagmodulo.infrastructure.persistence.order.entity.ChargeEntityJpa;
import com.csf.pagmodulo.infrastructure.persistence.order.entity.OrderEntityJpa;
import com.csf.pagmodulo.infrastructure.persistence.order.entity.enums.BillingStatusJpa;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        uses = {AmountPersistenceMapper.class, PaymentResponsePersistenceMapper.class, PaymentInstructionPersistenceMapper.class}
)
public abstract class ChargePersistenceMapper {

    @Autowired
    protected PaymentInstructionPersistenceMapper paymentInstructionMapper;

    @Autowired
    protected AmountPersistenceMapper amountPersistenceMapper;

    @Autowired
    protected PaymentResponsePersistenceMapper paymentResponsePersistenceMapper;


    // ---- toEntity ----

    @Mapping(target = "id",              ignore = true)
    @Mapping(target = "uuid",            expression = "java(charge.getId().value())")
    @Mapping(target = "status",          expression = "java(toBillingStatusJpa(charge.getStatus()))")
    @Mapping(target = "createdAt",       source = "created_at")
    @Mapping(target = "paidAt",          source = "paid_at")
    @Mapping(target = "amount",          source = "amount")
    @Mapping(target = "paymentResponse", source = "paymentResponse")
    @Mapping(target = "fine",            ignore = true)
    @Mapping(target = "interest",        ignore = true)
    @Mapping(target = "discounts",       ignore = true)
    @Mapping(target = "order",           ignore = true)
    protected abstract ChargeEntityJpa toEntityBase(Charge charge);

    public ChargeEntityJpa toEntity(Charge charge, OrderEntityJpa orderEntity) {
        if (charge == null) return null;
        ChargeEntityJpa entity = toEntityBase(charge);
        entity.setOrder(orderEntity);
        if (charge.getPaymentInstruction() != null) {
            PaymentInstruction pi = charge.getPaymentInstruction();
            entity.setFine(paymentInstructionMapper.toFineEmbeddable(pi.fine()));
            entity.setInterest(paymentInstructionMapper.toInterestEmbeddable(pi.interest()));
            entity.setDiscounts(paymentInstructionMapper.toDiscountEmbeddableList(pi.discounts()));
        }
        return entity;
    }

    public List<ChargeEntityJpa> toEntityList(List<Charge> charges, OrderEntityJpa orderEntity) {
        if (charges == null) return List.of();
        return charges.stream().map(c -> toEntity(c, orderEntity)).collect(Collectors.toList());
    }

    // ---- toDomain ----

    public Charge toDomain(ChargeEntityJpa entity) {
        if (entity == null) return null;

        PaymentInstruction pi = null;
        if (entity.getFine() != null && entity.getInterest() != null
                && entity.getDiscounts() != null && !entity.getDiscounts().isEmpty()) {
            pi = paymentInstructionMapper.toDomain(entity.getFine(), entity.getInterest(), entity.getDiscounts());
        }

        return new Charge(
                new ChargeId(entity.getUuid()),
                entity.getGatewayId(),
                toBillingStatusDomain(entity.getStatus()),
                entity.getCreatedAt(),
                entity.getPaidAt(),
                entity.getDescription(),
                amountPersistenceMapper.toDomain(entity.getAmount()),
                paymentResponsePersistenceMapper.toDomain(entity.getPaymentResponse()),
                pi
        );
    }

    public List<Charge> toDomainList(List<ChargeEntityJpa> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(this::toDomain).collect(Collectors.toList());
    }

    // ---- Status converters ----

    protected BillingStatusJpa toBillingStatusJpa(BillingStatus status) {
        if (status == null) return null;
        return BillingStatusJpa.valueOf(status.name());
    }

    protected BillingStatus toBillingStatusDomain(BillingStatusJpa status) {
        if (status == null) return null;
        return BillingStatus.valueOf(status.name());
    }
}


