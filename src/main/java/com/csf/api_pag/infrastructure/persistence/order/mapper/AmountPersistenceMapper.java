package com.csf.api_pag.infrastructure.persistence.order.mapper;

import com.csf.api_pag.domain.order.valueobject.Amount;
import com.csf.api_pag.domain.shared.valueobjects.Money;
import com.csf.api_pag.infrastructure.persistence.order.embeddable.AmountEmbeddableJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SummaryPersistenceMapper.class)
public interface AmountPersistenceMapper {

    @Mapping(target = "moneyValue",    source = "money.value")
    @Mapping(target = "moneyCurrency", source = "money.currency")
    @Mapping(target = "summary",       source = "summary")
    AmountEmbeddableJpa toEmbeddable(Amount amount);

    @Mapping(target = "money",   expression = "java(new Money(embeddable.getMoneyValue(), embeddable.getMoneyCurrency()))")
    @Mapping(target = "summary", source = "summary")
    Amount toDomain(AmountEmbeddableJpa embeddable);

    default Money toMoney(int value, String currency) {
        return new Money(value, currency);
    }
}

