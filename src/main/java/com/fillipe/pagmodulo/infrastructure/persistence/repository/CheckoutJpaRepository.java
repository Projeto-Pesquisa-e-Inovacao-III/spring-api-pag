package com.fillipe.pagmodulo.infrastructure.persistence.repository;

import com.fillipe.pagmodulo.infrastructure.persistence.entity.CheckoutEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutJpaRepository extends JpaRepository<CheckoutEntityJpa, Long> {

}
