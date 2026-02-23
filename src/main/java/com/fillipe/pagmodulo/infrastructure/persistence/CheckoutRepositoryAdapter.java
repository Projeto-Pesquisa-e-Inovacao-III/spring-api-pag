package com.fillipe.pagmodulo.infrastructure.persistence;

import com.fillipe.pagmodulo.domain.entity.Checkout;
import com.fillipe.pagmodulo.domain.port.CheckoutRepository;
import com.fillipe.pagmodulo.infrastructure.persistence.entity.CheckoutEntityJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.mapper.CheckoutPersistenceMapper;
import com.fillipe.pagmodulo.infrastructure.persistence.repository.CheckoutJpaRepository;
import org.springframework.stereotype.Component;

@Component
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
        System.out.println(checkout);
        CheckoutEntityJpa entity = mapper.toEntity(checkout);
        System.out.println(entity.toString());
        CheckoutEntityJpa saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

//    @Override
//    public Optional<Checkout> findById(Long externalCustomerId) {
//        return jpaRepository.findById(externalCustomerId).map(mapper::toDomain);
//    }
//
//    @Override
//    public Optional<Checkout> findByUuid(UUID uuid) {
//        return jpaRepository.findByUuid(uuid).map(mapper::toDomain);
//    }
//
//    @Override
//    public void delete(Checkout checkout) {
//        jpaRepository.deleteById(checkout.getId());
//    }
}

