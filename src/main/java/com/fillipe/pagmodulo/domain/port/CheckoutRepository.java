package com.fillipe.pagmodulo.domain.port;

import com.fillipe.pagmodulo.domain.entity.Checkout;

import java.util.Optional;
import java.util.UUID;

public interface CheckoutRepository {

    Checkout save(Checkout checkout);

//    Optional<Checkout> findById(Long externalCustomerId);
//
//    Optional<Checkout> findByUuid(UUID uuid);
//
//    void delete(Checkout checkout);
}