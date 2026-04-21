package com.csf.api_pag.presentation.mapper.checkout;

import com.csf.api_pag.presentation.dto.checkout.PaymentConfigOptionDto;
import com.csf.api_pag.domain.checkout.valueobject.paymentMethod.PaymentConfigOption;

public class PaymentConfigOptionMapper {

    private PaymentConfigOptionMapper() {}

    public static PaymentConfigOptionDto toPaymentConfigOptionDTO(PaymentConfigOption paymentConfigOption) {
        if (paymentConfigOption == null) return null;
        return new PaymentConfigOptionDto(paymentConfigOption.option(), paymentConfigOption.value());
    }

    public static PaymentConfigOption toPaymentConfigOption(PaymentConfigOptionDto paymentConfigOptionDTO) {
        if (paymentConfigOptionDTO == null) return null;
        return new PaymentConfigOption(paymentConfigOptionDTO.option(), paymentConfigOptionDTO.value());
    }
}
