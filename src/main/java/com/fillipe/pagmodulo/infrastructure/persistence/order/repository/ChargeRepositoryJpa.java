package com.fillipe.pagmodulo.infrastructure.persistence.order.repository;

import com.fillipe.pagmodulo.infrastructure.persistence.order.entity.ChargeEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepositoryJpa extends JpaRepository<ChargeEntityJpa, Long> {
}
