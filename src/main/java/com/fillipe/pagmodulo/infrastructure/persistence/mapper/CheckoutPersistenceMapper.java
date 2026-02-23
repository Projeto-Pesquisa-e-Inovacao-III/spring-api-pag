package com.fillipe.pagmodulo.infrastructure.persistence.mapper;

import com.fillipe.pagmodulo.domain.entity.Checkout;
import com.fillipe.pagmodulo.domain.valueobject.Customer;
import com.fillipe.pagmodulo.domain.valueobject.Item;
import com.fillipe.pagmodulo.domain.valueobject.Link;
import com.fillipe.pagmodulo.domain.valueobject.PaymentMethod;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

        CustomerEntityJpa customer = customerMapper.toEntity(checkout.getCustomer());

        List<ItemEntityJpa> items = checkout.getItems() == null ? List.of()
                : checkout.getItems().stream().map(itemMapper::toEntity).collect(Collectors.toList());

        List<PaymentMethodEntityJpa> paymentMethods = checkout.getPaymentMethods() == null ? List.of()
                : checkout.getPaymentMethods().stream().map(paymentMethodMapper::toEntity).collect(Collectors.toList());

        List<LinkEntityJpa> links = checkout.getLinks() == null ? List.of()
                : checkout.getLinks().stream().map(linkMapper::toEntity).collect(Collectors.toList());

        CheckoutEntityJpa entity = new CheckoutEntityJpa();
        entity.setId(checkout.getId());
        entity.setUuid(checkout.getUuid());
        entity.setExternalId(checkout.getExternalId());
        entity.setExpirationDate(checkout.getExpirationDate());
        entity.setCreatedAt(checkout.getCreatedAt());
        entity.setStatus(checkout.getStatus());
        entity.setCustomer(customer);
        entity.setCustomerModifiable(checkout.getCustomerModifiable());
        entity.setItems(items);
        entity.setAdditionalAmount(checkout.getAdditionalAmount());
        entity.setDiscountAmount(checkout.getDiscountAmount());
        entity.setPaymentMethods(paymentMethods);
        entity.setSoftDescriptor(checkout.getSoftDescriptor());
        entity.setRedirectUrl(checkout.getRedirectUrl());
        entity.setReturnUrl(checkout.getReturnUrl());
        entity.setNotificationUrls(checkout.getNotificationUrls());
        entity.setPaymentNotificationUrls(checkout.getPaymentNotificationUrls());
        entity.setLinks(links);

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
                entity.getId(),
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
