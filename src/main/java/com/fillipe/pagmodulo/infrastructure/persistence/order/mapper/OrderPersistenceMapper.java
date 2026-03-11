package com.fillipe.pagmodulo.infrastructure.persistence.order.mapper;

import com.fillipe.pagmodulo.domain.order.entity.Charge;
import com.fillipe.pagmodulo.domain.order.entity.Order;
import com.fillipe.pagmodulo.domain.shared.valueobjects.CheckoutId;
import com.fillipe.pagmodulo.domain.shared.valueobjects.OrderId;
import com.fillipe.pagmodulo.infrastructure.persistence.order.entity.ChargeEntityJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.order.entity.OrderEntityJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.shared.mapper.CustomerPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderPersistenceMapper {

    @Autowired
    private CustomerPersistenceMapper customerMapper;

    @Autowired
    private ChargePersistenceMapper chargeMapper;

    // ---- toEntity ----

    public OrderEntityJpa toEntity(Order order) {
        if (order == null) return null;

        OrderEntityJpa entity = new OrderEntityJpa();
        entity.setUuid(order.getOrderId().value());
        entity.setCheckoutId(order.getCheckoutId().value());
        entity.setGatewayOrderId(order.getGatewayOrderId());
        entity.setCreatedAt(order.getCreatedAt());
        entity.setCustomer(customerMapper.toEmbeddable(order.getCustomer()));
        entity.setCharges(chargeMapper.toEntityList(order.getCharges(), entity));

        return entity;
    }

    public OrderEntityJpa toEntity(Order order, Long id) {
        OrderEntityJpa entity = toEntity(order);
        if (entity != null) entity.setId(id);
        return entity;
    }

    // ---- toDomain ----

    public Order toDomain(OrderEntityJpa entity) {
        if (entity == null) return null;

        List<Charge> charges = chargeMapper.toDomainList(entity.getCharges());

        return Order.builder()
                .orderId(new OrderId(entity.getUuid()))
                .checkoutId(new CheckoutId(entity.getCheckoutId()))
                .gatewayOrderId(entity.getGatewayOrderId())
                .createdAt(entity.getCreatedAt())
                .customer(customerMapper.toDomain(entity.getCustomer()))
                .items(List.of())
                .charges(charges)
                .build();
    }
}

