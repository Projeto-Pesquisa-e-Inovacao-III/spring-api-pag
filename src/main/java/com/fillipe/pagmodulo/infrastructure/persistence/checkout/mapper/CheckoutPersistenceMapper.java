package com.fillipe.pagmodulo.infrastructure.persistence.checkout.mapper;

import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;
import com.fillipe.pagmodulo.domain.checkout.enums.CheckoutStatus;
import com.fillipe.pagmodulo.domain.shared.valueobjects.Customer;
import com.fillipe.pagmodulo.domain.shared.valueobjects.Item;
import com.fillipe.pagmodulo.domain.checkout.valueobject.paymentMethod.PaymentMethod;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.entity.CheckoutEntityJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.entity.enums.CheckoutStatusJpa;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {
                CustomerPersistenceMapper.class,
                ItemPersistenceMapper.class,
                PaymentMethodPersistenceMapper.class
        })
public abstract class CheckoutPersistenceMapper {

    @Autowired
    protected CustomerPersistenceMapper customerMapper;
    @Autowired
    protected ItemPersistenceMapper itemMapper;
    @Autowired
    protected PaymentMethodPersistenceMapper paymentMethodMapper;

    public CheckoutEntityJpa toEntity(Checkout checkout) {
        if (checkout == null) return null;

        CheckoutEntityJpa entity = new CheckoutEntityJpa();
        entity.setUuid(checkout.getId());
        entity.setGatewayId(checkout.getGatewayId());
        entity.setExpirationDate(checkout.getExpirationDate());
        entity.setCreatedAt(checkout.getCreatedAt());
        entity.setStatus(toStatusJpa(checkout.getStatus()));
        entity.setCustomer(customerMapper.toEmbeddable(checkout.getCustomer()));
        entity.setItems(itemMapper.toEntityList(checkout.getItems(), entity));
        entity.setAdditionalAmount(checkout.getAdditionalAmount());
        entity.setDiscountAmount(checkout.getDiscountAmount());
        entity.setPaymentMethods(paymentMethodMapper.toEmbeddableList(checkout.getPaymentMethods()));
        entity.setConfigOptions(paymentMethodMapper.toConfigOptionEmbeddableList(checkout.getPaymentMethods()));
        return entity;
    }

    public CheckoutEntityJpa toEntity(Checkout checkout, Long id) {
        CheckoutEntityJpa builtEntity = toEntity(checkout);
        builtEntity.setId(id);
        return builtEntity;
    }

    public Checkout toDomain(CheckoutEntityJpa entity) {
        if (entity == null) return null;

        Customer customer = customerMapper.toDomain(entity.getCustomer());

        List<Item> items = entity.getItems() == null ? List.of()
                : entity.getItems().stream().map(itemMapper::toDomain).collect(Collectors.toList());

        List<PaymentMethod> paymentMethods =
                paymentMethodMapper.toDomainList(entity.getPaymentMethods(), entity.getConfigOptions());

        return Checkout.fromExisting()
                .uuid(entity.getUuid())
                .gatewayId(entity.getGatewayId())
                .expirationDate(entity.getExpirationDate())
                .createdAt(entity.getCreatedAt())
                .status(toStatusDomain(entity.getStatus()))
                .customer(customer)
                .items(items)
                .additionalAmount(entity.getAdditionalAmount())
                .discountAmount(entity.getDiscountAmount())
                .paymentMethods(paymentMethods)
                .build();
    }

    public Optional<Checkout> toDomainOptional(Optional<CheckoutEntityJpa> optionalCheckoutEntityJpa) {
        return optionalCheckoutEntityJpa.map(this::toDomain);
    }

    public Optional<CheckoutEntityJpa> toEntityOptional(Optional<Checkout> optionalCheckout) {
        return optionalCheckout.map(this::toEntity);
    }

    private CheckoutStatusJpa toStatusJpa(CheckoutStatus status) {
        if (status == null) return null;
        return switch (status) {
            case ACTIVE -> CheckoutStatusJpa.ACTIVE;
            case INACTIVE, CREATING, PAID -> CheckoutStatusJpa.INACTIVE;
            case EXPIRED -> CheckoutStatusJpa.EXPIRED;
        };
    }

    private CheckoutStatus toStatusDomain(CheckoutStatusJpa status) {
        if (status == null) return null;
        return switch (status) {
            case ACTIVE -> CheckoutStatus.ACTIVE;
            case INACTIVE -> CheckoutStatus.INACTIVE;
            case EXPIRED -> CheckoutStatus.EXPIRED;
        };
    }
}
