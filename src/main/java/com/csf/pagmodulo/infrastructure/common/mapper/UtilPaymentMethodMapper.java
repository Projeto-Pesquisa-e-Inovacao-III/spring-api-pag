package com.csf.pagmodulo.infrastructure.common.mapper;
import com.csf.pagmodulo.domain.checkout.valueobject.paymentMethod.*;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UtilPaymentMethodMapper {

    @Named("fromPaymentMethodType")
    default String fromPaymentMethodType(PaymentMethod paymentMethod) {
        if (paymentMethod == null) return null;
        return switch (paymentMethod) {
            case PixPaymentMethod ignored -> "PIX";
            case BoletoPaymentMethod ignored -> "BOLETO";
            case CreditCardPaymentMethod ignored -> "CREDIT_CARD";
            case DebitCardPaymentMethod ignored -> "DEBIT_CARD";
        };
    }
}
