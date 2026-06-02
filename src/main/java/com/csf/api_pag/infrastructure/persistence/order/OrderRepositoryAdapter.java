package com.csf.api_pag.infrastructure.persistence.order;

import com.csf.api_pag.domain.order.entity.Order;
import com.csf.api_pag.domain.order.port.OrderRepository;
import com.csf.api_pag.infrastructure.persistence.order.entity.OrderEntityJpa;
import com.csf.api_pag.infrastructure.persistence.order.mapper.OrderPersistenceMapper;
import com.csf.api_pag.infrastructure.persistence.order.repository.OrderRepositoryJpa;
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
