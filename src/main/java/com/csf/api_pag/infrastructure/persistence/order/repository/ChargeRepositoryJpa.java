package com.csf.api_pag.infrastructure.persistence.order.repository;

import com.csf.api_pag.infrastructure.persistence.order.entity.ChargeEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepositoryJpa extends JpaRepository<ChargeEntityJpa, Long> {
}
