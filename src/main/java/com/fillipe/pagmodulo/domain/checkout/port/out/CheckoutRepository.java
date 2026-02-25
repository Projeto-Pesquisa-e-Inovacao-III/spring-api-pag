package com.fillipe.pagmodulo.domain.checkout.port.out;

import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;

import java.util.Optional;
import java.util.UUID;

public interface CheckoutRepository {

    Checkout save(Checkout checkout);

    Optional<Checkout> findByUuid(UUID uuid);

    Optional<Checkout> findByGatewayId(String gatewayId);

    boolean existsByGatewayId(String gatewayId);

    boolean existsByUuid(UUID uuid);
}