package com.fillipe.pagmodulo.infrastructure.persistence.mapper;

import com.fillipe.pagmodulo.domain.entity.Checkout;
import com.fillipe.pagmodulo.domain.valueobject.Customer;
import com.fillipe.pagmodulo.domain.valueobject.Item;
import com.fillipe.pagmodulo.domain.valueobject.Link;
import com.fillipe.pagmodulo.domain.valueobject.PaymentMethod;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.*;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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

        CheckoutEntityJpa entity = new CheckoutEntityJpa();
        entity.setUuid(checkout.getUuid());
        entity.setExternalId(checkout.getGatewayId());
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

    /**
     * Manual mapping to toDomain — avoids ambiguous constructor issue without
     * polluting the domain class with @Default (Clean Architecture).
     */
    public Checkout toDomain(CheckoutEntityJpa entity) {
        if (entity == null) return null;

        Customer customer = customerMapper.toDomain(entity.getCustomer());

        List<Item> items = entity.getItems() == null ? List.of()
                : entity.getItems().stream().map(itemMapper::toDomain).collect(Collectors.toList());

        List<PaymentMethod> paymentMethods = entity.getPaymentMethods() == null ? List.of()
                : entity.getPaymentMethods().stream().map(paymentMethodMapper::toDomain).collect(Collectors.toList());

        List<Link> links = entity.getLinks() == null ? List.of()
                : entity.getLinks().stream().map(linkMapper::toDomain).collect(Collectors.toList());

        return new Checkout(
                entity.getUuid(),
                entity.getExternalId(),
                entity.getExpirationDate(),
                entity.getCreatedAt(),
                entity.getStatus(),
                customer,
                entity.getCustomerModifiable(),
                items,
                entity.getAdditionalAmount(),
                entity.getDiscountAmount(),
                paymentMethods,
                entity.getSoftDescriptor(),
                entity.getRedirectUrl(),
                entity.getReturnUrl(),
                entity.getNotificationUrls(),
                entity.getPaymentNotificationUrls(),
                links
        );
    }
}
