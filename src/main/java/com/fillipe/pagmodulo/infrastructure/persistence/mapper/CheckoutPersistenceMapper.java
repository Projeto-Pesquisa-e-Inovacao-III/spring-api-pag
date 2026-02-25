package com.fillipe.pagmodulo.infrastructure.persistence.mapper;

import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;
import com.fillipe.pagmodulo.domain.checkout.valueobject.Customer;
import com.fillipe.pagmodulo.domain.checkout.valueobject.Item;
import com.fillipe.pagmodulo.domain.checkout.valueobject.Link;
import com.fillipe.pagmodulo.domain.checkout.valueobject.PaymentMethod;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.*;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {
                CustomerPersistenceMapper.class,
                ItemPersistenceMapper.class,
                LinkPersistenceMapper.class,
                PaymentMethodPersistenceMapper.class
        })
public abstract class CheckoutPersistenceMapper {

    @Autowired
    protected CustomerPersistenceMapper customerMapper;
    @Autowired
    protected ItemPersistenceMapper itemMapper;
    @Autowired
    protected LinkPersistenceMapper linkMapper;
    @Autowired
    protected PaymentMethodPersistenceMapper paymentMethodMapper;

    public CheckoutEntityJpa toEntity(Checkout checkout) {
        if (checkout == null) return null;

        System.out.println("TO ENTITY: "+ checkout.getGatewayId());

        CheckoutEntityJpa entity = new CheckoutEntityJpa();
        entity.setUuid(checkout.getUuid());
        entity.setGatewayId(checkout.getGatewayId());
        entity.setExpirationDate(checkout.getExpirationDate());
        entity.setCreatedAt(checkout.getCreatedAt());
        entity.setStatus(checkout.getStatus());
        entity.setCustomer(customerMapper.toEntity(checkout.getCustomer(), entity));
        entity.setCustomerModifiable(checkout.getCustomerModifiable());
        entity.setItems(itemMapper.toEntityList(checkout.getItems(), entity));
        entity.setAdditionalAmount(checkout.getAdditionalAmount());
        entity.setDiscountAmount(checkout.getDiscountAmount());
        entity.setPaymentMethods(paymentMethodMapper.toEntityList(checkout.getPaymentMethods(), entity));
        entity.setSoftDescriptor(checkout.getSoftDescriptor());
        entity.setRedirectUrl(checkout.getRedirectUrl());
        entity.setReturnUrl(checkout.getReturnUrl());
        entity.setNotificationUrls(checkout.getNotificationUrls());
        entity.setPaymentNotificationUrls(checkout.getPaymentNotificationUrls());
        entity.setLinks(linkMapper.toEntityList(checkout.getLinks(), entity));

        return entity;
    }

    public CheckoutEntityJpa toEntity(Checkout checkout, Long id){
        CheckoutEntityJpa buildedEntity = toEntity(checkout);
        buildedEntity.setId(id);
        return buildedEntity;
    }

    public Checkout toDomain(CheckoutEntityJpa entity) {
        if (entity == null) return null;

        Customer customer = customerMapper.toDomain(entity.getCustomer());

        List<Item> items = entity.getItems() == null ? List.of()
                : entity.getItems().stream().map(itemMapper::toDomain).collect(Collectors.toList());

        List<PaymentMethod> paymentMethods = entity.getPaymentMethods() == null ? List.of()
                : entity.getPaymentMethods().stream().map(paymentMethodMapper::toDomain).collect(Collectors.toList());

        List<Link> links = entity.getLinks() == null ? List.of()
                : entity.getLinks().stream().map(linkMapper::toDomain).collect(Collectors.toList());

        return Checkout.fromExisting()
                .uuid(entity.getUuid())
                .gatewayId(entity.getGatewayId())
                .expirationDate(entity.getExpirationDate())
                .createdAt(entity.getCreatedAt())
                .status(entity.getStatus())
                .customer(customer)
                .customerModifiable(entity.getCustomerModifiable())
                .items(items)
                .additionalAmount(entity.getAdditionalAmount())
                .discountAmount(entity.getDiscountAmount())
                .paymentMethods(paymentMethods)
                .softDescriptor(entity.getSoftDescriptor())
                .redirectUrl(entity.getRedirectUrl())
                .returnUrl(entity.getReturnUrl())
                .notificationUrls(entity.getNotificationUrls())
                .paymentNotificationUrls(entity.getPaymentNotificationUrls())
                .links(links)
                .build();
    }

    public Optional<Checkout> toDomainOptional(Optional<CheckoutEntityJpa> optionalCheckoutEntityJpa) {
        return optionalCheckoutEntityJpa.map(this::toDomain);
    }

    public Optional<CheckoutEntityJpa> toEntityOptional(Optional<Checkout> optionalCheckout) {
        return optionalCheckout.map(this::toEntity);
    }
}
