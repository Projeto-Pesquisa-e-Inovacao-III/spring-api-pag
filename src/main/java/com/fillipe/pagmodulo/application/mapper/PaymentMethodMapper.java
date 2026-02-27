package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.PaymentMethodDto;
import com.fillipe.pagmodulo.domain.checkout.valueobject.PaymentMethod;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMethodMapper {

    private PaymentMethodMapper() {}

    public static PaymentMethodDto toPaymentMethodDTO(PaymentMethod paymentMethod) {
        if (paymentMethod == null) return null;
        return new PaymentMethodDto(
                paymentMethod.type(),
                paymentMethod.configOptions() == null ? null :
                        paymentMethod.configOptions().stream()
                                .map(PaymentConfigOptionMapper::toPaymentConfigOptionDTO)
                                .collect(Collectors.toList())
        );
    }

    public static PaymentMethod toPaymentMethod(PaymentMethodDto paymentMethodDTO) {
        if (paymentMethodDTO == null) return null;
        return new PaymentMethod(
                paymentMethodDTO.type(),
                paymentMethodDTO.configOptions() == null ? null :
                        paymentMethodDTO.configOptions().stream()
                                .map(PaymentConfigOptionMapper::toPaymentConfigOption)
                                .collect(Collectors.toList())
        );
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
