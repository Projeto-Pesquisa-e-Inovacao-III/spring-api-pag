package com.fillipe.pagmodulo.infrastructure.persistence.order;

import com.fillipe.pagmodulo.domain.order.entity.Order;
import com.fillipe.pagmodulo.domain.order.port.OrderRepository;
import com.fillipe.pagmodulo.infrastructure.persistence.order.entity.OrderEntityJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.order.mapper.OrderPersistenceMapper;
import com.fillipe.pagmodulo.infrastructure.persistence.order.repository.OrderRepositoryJpa;
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

}
