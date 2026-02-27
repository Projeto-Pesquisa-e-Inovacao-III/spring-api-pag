package com.fillipe.pagmodulo.infrastructure.persistence;

import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;
import com.fillipe.pagmodulo.domain.checkout.port.out.CheckoutRepository;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.CheckoutEntityJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.mapper.CheckoutPersistenceMapper;
import com.fillipe.pagmodulo.infrastructure.persistence.repository.CheckoutJpaRepository;
import com.fillipe.pagmodulo.infrastructure.persistence.repository.projection.GatewayIdProjection;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CheckoutRepositoryAdapter implements CheckoutRepository {

    private final CheckoutJpaRepository jpaRepository;
    private final CheckoutPersistenceMapper mapper;

    public CheckoutRepositoryAdapter(CheckoutJpaRepository jpaRepository,
                                     CheckoutPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Checkout save(Checkout checkout) {
        CheckoutEntityJpa entity;
        Optional<CheckoutEntityJpa> maybeEntityInDb = jpaRepository.findByUuid(checkout.getUuid());

        if(maybeEntityInDb.isPresent()) {
            entity = mapper.toEntity(checkout, maybeEntityInDb.get().getId());
        } else {
            entity = mapper.toEntity(checkout);
        }

        CheckoutEntityJpa saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Checkout> findByUuid(UUID uuid) {
        return mapper.toDomainOptional(jpaRepository.findByUuid(uuid));
    }

    @Override
    public Optional<Checkout> findByGatewayId(String gatewayId) {
        return mapper.toDomainOptional(jpaRepository.findByGatewayId(gatewayId));
    }

    @Override
    public Optional<String> getGatewayIdByUuid(UUID uuid) {
        return jpaRepository.findGatewayIdByUuid(uuid)
                .map(GatewayIdProjection::getGatewayId);
    }

    @Override
    public boolean existsByGatewayId(String gatewayId) {
        return jpaRepository.existsByGatewayId(gatewayId);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return jpaRepository.existsByUuid(uuid);
    }
}

