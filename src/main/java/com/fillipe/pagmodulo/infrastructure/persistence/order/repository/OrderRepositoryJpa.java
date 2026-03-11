package com.fillipe.pagmodulo.infrastructure.persistence.order.repository;

import com.fillipe.pagmodulo.infrastructure.persistence.order.entity.OrderEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryJpa extends JpaRepository<OrderEntityJpa, Long> {
}
