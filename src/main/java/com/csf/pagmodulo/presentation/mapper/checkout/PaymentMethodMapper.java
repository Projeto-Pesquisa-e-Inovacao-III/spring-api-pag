package com.csf.pagmodulo.presentation.mapper.checkout;

import com.csf.pagmodulo.presentation.dto.checkout.PaymentMethodDto;
import com.csf.pagmodulo.presentation.dto.checkout.enums.PaymentMethodType;
import com.csf.pagmodulo.domain.checkout.valueobject.paymentMethod.*;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMethodMapper {

    private PaymentMethodMapper() {}

    public static PaymentMethodDto toPaymentMethodDTO(PaymentMethod paymentMethod) {
        if (paymentMethod == null) return null;
        return switch (paymentMethod) {
            case PixPaymentMethod ignored -> new PaymentMethodDto(PaymentMethodType.PIX, null);
            case BoletoPaymentMethod ignored -> new PaymentMethodDto(PaymentMethodType.BOLETO, null);
            case CreditCardPaymentMethod c -> new PaymentMethodDto(
                    PaymentMethodType.CREDIT_CARD,
                    c.configOptions().stream()
                            .map(PaymentConfigOptionMapper::toPaymentConfigOptionDTO)
                            .collect(Collectors.toList())
            );
            case DebitCardPaymentMethod d -> new PaymentMethodDto(
                    PaymentMethodType.DEBIT_CARD,
                    d.configOptions().stream()
                            .map(PaymentConfigOptionMapper::toPaymentConfigOptionDTO)
                            .collect(Collectors.toList())
            );
        };
    }

    public static PaymentMethod toPaymentMethod(PaymentMethodDto dto) {
        if (dto == null) return null;
        List<PaymentConfigOption> options = dto.configOptions() == null ? null :
                dto.configOptions().stream()
                        .map(PaymentConfigOptionMapper::toPaymentConfigOption)
                        .collect(Collectors.toList());
        return switch (dto.type()) {
            case PIX -> new PixPaymentMethod();
            case BOLETO -> new BoletoPaymentMethod();
            case CREDIT_CARD -> new CreditCardPaymentMethod(options);
            case DEBIT_CARD -> new DebitCardPaymentMethod(options);
        };
    }

    public static List<PaymentMethodDto> toPaymentMethodDTOList(List<PaymentMethod> paymentMethods) {
        if (paymentMethods == null) return null;
        return paymentMethods.stream().map(PaymentMethodMapper::toPaymentMethodDTO).collect(Collectors.toList());
    }

    public static List<PaymentMethod> toPaymentMethodList(List<PaymentMethodDto> paymentMethodDTOs) {
        if (paymentMethodDTOs == null) return null;
        return paymentMethodDTOs.stream().map(PaymentMethodMapper::toPaymentMethod).collect(Collectors.toList());
    }
}
