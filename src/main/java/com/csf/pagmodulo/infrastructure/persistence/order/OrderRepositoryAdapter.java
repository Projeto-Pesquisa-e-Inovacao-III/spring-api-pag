package com.csf.pagmodulo.infrastructure.persistence.order;

import com.csf.pagmodulo.domain.order.entity.Order;
import com.csf.pagmodulo.domain.order.port.OrderRepository;
import com.csf.pagmodulo.infrastructure.persistence.order.entity.OrderEntityJpa;
import com.csf.pagmodulo.infrastructure.persistence.order.mapper.OrderPersistenceMapper;
import com.csf.pagmodulo.infrastructure.persistence.order.repository.OrderRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderPersistenceMapper mapper;

    private final OrderRepositoryJpa repository;

    public OrderRepositoryAdapter(OrderPersistenceMapper mapper, OrderRepositoryJpa repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        OrderEntityJpa entity = repository.save(mapper.toEntity(order));
        return mapper.toDomain(entity);
    }

    @Override
    public boolean existsByGatewayOrderId(String gatewayOrderId) {
        return repository.existsByGatewayOrderId(gatewayOrderId);
    }
}
