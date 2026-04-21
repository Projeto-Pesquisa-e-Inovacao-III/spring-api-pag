package com.csf.api_pag.infrastructure.persistence.order.mapper;

import com.csf.api_pag.domain.order.entity.Charge;
import com.csf.api_pag.domain.order.entity.Order;
import com.csf.api_pag.domain.shared.valueobjects.CheckoutId;
import com.csf.api_pag.domain.shared.valueobjects.Item;
import com.csf.api_pag.domain.shared.valueobjects.OrderId;
import com.csf.api_pag.infrastructure.persistence.order.entity.OrderEntityJpa;
import com.csf.api_pag.infrastructure.persistence.shared.mapper.CustomerPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderPersistenceMapper {

    @Autowired
    private CustomerPersistenceMapper customerMapper;

    @Autowired
    private ChargePersistenceMapper chargeMapper;

    @Autowired
    private OrderItemPersistenceMapper orderItemMapper;


    public OrderEntityJpa toEntity(Order order) {
        if (order == null) return null;

        OrderEntityJpa entity = new OrderEntityJpa();
        entity.setUuid(order.getOrderId().value());
        entity.setCheckoutId(order.getCheckoutId().value());
        entity.setGatewayOrderId(order.getGatewayOrderId());
        entity.setCreatedAt(order.getCreatedAt());
        entity.setCustomer(customerMapper.toEmbeddable(order.getCustomer()));
        entity.setCharges(chargeMapper.toEntityList(order.getCharges(), entity));
        entity.setItens(orderItemMapper.toEntityList(order.getItems(), entity));

        return entity;
    }

    public OrderEntityJpa toEntity(Order order, Long id) {
        OrderEntityJpa entity = toEntity(order);
        if (entity != null) entity.setId(id);
        return entity;
    }

    public Order toDomain(OrderEntityJpa entity) {
        if (entity == null) return null;

        List<Charge> charges = chargeMapper.toDomainList(entity.getCharges());
        List<Item> items = orderItemMapper.toDomainList(entity.getItens());

        return Order.fromExisting()
                .orderId(new OrderId(entity.getUuid()))
                .checkoutId(new CheckoutId(entity.getCheckoutId()))
                .gatewayOrderId(entity.getGatewayOrderId())
                .createdAt(entity.getCreatedAt())
                .customer(customerMapper.toDomain(entity.getCustomer()))
                .items(items)
                .charges(charges)
                .build();
    }
}

