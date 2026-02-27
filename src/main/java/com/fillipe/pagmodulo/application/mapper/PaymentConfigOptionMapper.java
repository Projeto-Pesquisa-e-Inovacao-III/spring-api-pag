package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.PaymentConfigOptionDto;
import com.fillipe.pagmodulo.domain.checkout.valueobject.PaymentConfigOption;

public class PaymentConfigOptionMapper {

    private PaymentConfigOptionMapper() {}

    public static PaymentConfigOptionDto toPaymentConfigOptionDTO(PaymentConfigOption paymentConfigOption) {
        if (paymentConfigOption == null) return null;
        return new PaymentConfigOptionDto(paymentConfigOption.type(), paymentConfigOption.value());
    }

    public static PaymentConfigOption toPaymentConfigOption(PaymentConfigOptionDto paymentConfigOptionDTO) {
        if (paymentConfigOptionDTO == null) return null;
        return new PaymentConfigOption(paymentConfigOptionDTO.type(), paymentConfigOptionDTO.value());
    }
}
