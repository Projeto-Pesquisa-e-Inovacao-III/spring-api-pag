package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.PaymentMethodDto;
import com.fillipe.pagmodulo.domain.valueobject.PaymentMethod;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethodDto toPaymentMethodDTO(PaymentMethod paymentMethod);

    PaymentMethod toPaymentMethod(PaymentMethodDto paymentMethodDTO);

    List<PaymentMethodDto> toPaymentMethodDTOList(List<PaymentMethod> paymentMethods);

    List<PaymentMethod> toPaymentMethodList(List<PaymentMethodDto> paymentMethodDTOs);
}

