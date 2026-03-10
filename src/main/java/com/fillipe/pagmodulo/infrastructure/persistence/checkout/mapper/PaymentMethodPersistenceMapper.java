package com.fillipe.pagmodulo.infrastructure.persistence.checkout.mapper;

import com.fillipe.pagmodulo.domain.checkout.valueobject.paymentMethod.*;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.embeddable.PaymentConfigOptionEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.embeddable.PaymentMethodEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.embeddable.enums.PaymentConfigOptionTypeJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.embeddable.enums.PaymentTypeJpa;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PaymentMethodPersistenceMapper {

    default List<PaymentMethodEmbeddableJpa> toEmbeddableList(List<PaymentMethod> paymentMethods) {
        if (paymentMethods == null) return List.of();
        return paymentMethods.stream()
                .map(this::toEmbeddable)
                .collect(Collectors.toList());
    }

    default PaymentMethodEmbeddableJpa toEmbeddable(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case PixPaymentMethod ignored -> new PaymentMethodEmbeddableJpa(PaymentTypeJpa.PIX);
            case BoletoPaymentMethod ignored -> new PaymentMethodEmbeddableJpa(PaymentTypeJpa.BOLETO);
            case CreditCardPaymentMethod ignored -> new PaymentMethodEmbeddableJpa(PaymentTypeJpa.CREDIT_CARD);
            case DebitCardPaymentMethod ignored -> new PaymentMethodEmbeddableJpa(PaymentTypeJpa.DEBIT_CARD);
        };
    }

    default List<PaymentConfigOptionEmbeddableJpa> toConfigOptionEmbeddableList(List<PaymentMethod> paymentMethods) {
        if (paymentMethods == null) return List.of();
        List<PaymentConfigOptionEmbeddableJpa> result = new ArrayList<>();
        for (PaymentMethod pm : paymentMethods) {
            if (pm instanceof ConfigurableCardPaymentMethod configurable) {
                PaymentTypeJpa typeJpa = pm instanceof CreditCardPaymentMethod
                        ? PaymentTypeJpa.CREDIT_CARD : PaymentTypeJpa.DEBIT_CARD;
                for (PaymentConfigOption opt : configurable.configOptions()) {
                    result.add(new PaymentConfigOptionEmbeddableJpa(
                            typeJpa,
                            PaymentConfigOptionTypeJpa.valueOf(opt.option().name()),
                            opt.value()
                    ));
                }
            }
        }
        return result;
    }

    default List<PaymentMethod> toDomainList(List<PaymentMethodEmbeddableJpa> embeddables,
                                             List<PaymentConfigOptionEmbeddableJpa> configOptions) {
        if (embeddables == null) return List.of();
        return embeddables.stream()
                .map(e -> toDomain(e, configOptions))
                .collect(Collectors.toList());
    }

    default PaymentMethod toDomain(PaymentMethodEmbeddableJpa embeddable,
                                   List<PaymentConfigOptionEmbeddableJpa> configOptions) {
        return switch (embeddable.getType()) {
            case PIX -> new PixPaymentMethod();
            case BOLETO -> new BoletoPaymentMethod();
            case CREDIT_CARD -> new CreditCardPaymentMethod(
                    toConfigOptions(PaymentTypeJpa.CREDIT_CARD, configOptions));
            case DEBIT_CARD -> new DebitCardPaymentMethod(
                    toConfigOptions(PaymentTypeJpa.DEBIT_CARD, configOptions));
        };
    }

    default List<PaymentConfigOption> toConfigOptions(PaymentTypeJpa type,
                                                      List<PaymentConfigOptionEmbeddableJpa> configOptions) {
        if (configOptions == null) return List.of();
        return configOptions.stream()
                .filter(c -> c.getPaymentType() == type)
                .map(c -> new PaymentConfigOption(
                        PaymentConfigOptionType.valueOf(c.getType().name()),
                        c.getValue()
                ))
                .collect(Collectors.toList());
    }
}

