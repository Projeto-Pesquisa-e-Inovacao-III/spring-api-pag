package com.fillipe.pagmodulo.infrastructure.common.mapper;

import com.fillipe.pagmodulo.domain.valueobject.PaymentMethod;
import com.fillipe.pagmodulo.domain.valueobject.PaymentType;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UtilPaymentMethodMapper {
    @Named("fromPaymentMethodType")
    default String fromPaymentMethodType(PaymentMethod paymentMethod) {
        return paymentMethod != null ? paymentMethod.type().name() : null;
    }

    @Named("fromPaymentType")
    default String fromPaymentType(PaymentType type) {
        return type != null ? type.name() : null;
    }

    @Named("toPaymentType")
    default PaymentType toPaymentType(String type) {
        if (type == null) return null;
        return switch (type.toUpperCase()) {
            case "CREDIT_CARD" -> PaymentType.CREDIT_CARD;
            case "DEBIT_CARD" -> PaymentType.DEBIT_CARD;
            case "BOLETO" -> PaymentType.BOLETO;
            case "PIX" -> PaymentType.PIX;
            default -> null;
        };
    }

}
