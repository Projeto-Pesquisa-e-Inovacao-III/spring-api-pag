package com.csf.api_pag.infrastructure.persistence.order.repository;

import com.csf.api_pag.infrastructure.persistence.order.entity.OrderEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<OrderEntityJpa, Long> {
    boolean existsByGatewayOrderId(String gatewayOrderId);
}
