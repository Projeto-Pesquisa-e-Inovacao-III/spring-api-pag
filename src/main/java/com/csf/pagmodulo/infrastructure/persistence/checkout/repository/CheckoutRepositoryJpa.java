package com.csf.pagmodulo.infrastructure.persistence.checkout.repository;

import com.csf.pagmodulo.infrastructure.persistence.checkout.entity.CheckoutEntityJpa;
import com.csf.pagmodulo.infrastructure.persistence.checkout.repository.projection.GatewayIdProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CheckoutRepositoryJpa extends JpaRepository<CheckoutEntityJpa, Long> {

    Optional<CheckoutEntityJpa> findByUuid(UUID uuid);

    Optional<GatewayIdProjection> findGatewayIdByUuid(UUID uuid);

    Optional<CheckoutEntityJpa> findByGatewayId(String gatewayId);

    boolean existsByGatewayId(String gatewayId);

    boolean existsByUuid(UUID uuid);


}
