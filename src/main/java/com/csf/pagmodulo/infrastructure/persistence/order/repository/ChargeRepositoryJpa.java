package com.csf.pagmodulo.infrastructure.persistence.order.repository;

import com.csf.pagmodulo.infrastructure.persistence.order.entity.ChargeEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepositoryJpa extends JpaRepository<ChargeEntityJpa, Long> {
}
