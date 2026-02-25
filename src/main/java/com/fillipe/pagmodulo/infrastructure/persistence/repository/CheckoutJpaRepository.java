package com.fillipe.pagmodulo.infrastructure.persistence.repository;

import com.fillipe.pagmodulo.domain.entity.Checkout;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.CheckoutEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CheckoutJpaRepository extends JpaRepository<CheckoutEntityJpa, Long> {

    Optional<CheckoutEntityJpa> findByUuid(UUID uuid);

    Optional<CheckoutEntityJpa> findByGatewayId(String gatewayId);

    boolean existsByGatewayId(String gatewayId);

    boolean existsByUuid(UUID uuid);
}
