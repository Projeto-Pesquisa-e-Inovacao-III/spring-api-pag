package com.csf.pagmodulo.domain.checkout.port.out;

import com.csf.pagmodulo.domain.checkout.entity.Checkout;

import java.util.Optional;
import java.util.UUID;

public interface CheckoutRepository {

    Checkout save(Checkout checkout);

    Checkout update(Checkout checkout);

    Optional<Checkout> findByUuid(UUID uuid);

    Optional<Checkout> findByGatewayId(String gatewayId);

    Optional<String> getGatewayIdByUuid(UUID uuid);

    boolean existsByGatewayId(String gatewayId);

    boolean existsByUuid(UUID uuid);
}