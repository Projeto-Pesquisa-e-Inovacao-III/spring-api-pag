package com.fillipe.pagmodulo.infrastructure.persistence.order.repository;

import com.fillipe.pagmodulo.infrastructure.persistence.order.entity.OrderEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<OrderEntityJpa, Long> {
    boolean existsByGatewayOrderId(String gatewayOrderId);
}
