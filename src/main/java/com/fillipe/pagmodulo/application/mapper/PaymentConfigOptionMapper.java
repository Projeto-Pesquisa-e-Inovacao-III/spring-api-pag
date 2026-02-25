package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.PaymentConfigOptionDto;
import com.fillipe.pagmodulo.domain.valueobject.PaymentConfigOption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentConfigOptionMapper {

    PaymentConfigOptionDto toPaymentConfigOptionDTO(PaymentConfigOption paymentConfigOption);

    PaymentConfigOption toPaymentConfigOption(PaymentConfigOptionDto paymentConfigOptionDTO);
}

