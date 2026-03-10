package com.fillipe.pagmodulo.application.mapper.Checkout;

import com.fillipe.pagmodulo.application.dto.checkout.request.ReqCreateCheckoutDto;
import com.fillipe.pagmodulo.application.dto.checkout.response.ResCheckoutDto;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutCommand;
import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;

import java.time.LocalDateTime;

public class ApplicationCheckoutMapper {

    private ApplicationCheckoutMapper() {}

    public static CreateCheckoutCommand toCreateCheckoutCommand(ReqCreateCheckoutDto requestDTO) {
        if (requestDTO == null) return null;
        return new CreateCheckoutCommand(
                LocalDateTime.now().plusHours(1),
                CustomerMapper.toCustomer(requestDTO.customer()),
                ItemMapper.toItemList(requestDTO.items()),
                requestDTO.additionalAmount(),
                requestDTO.discountAmount(),
                PaymentMethodMapper.toPaymentMethodList(requestDTO.paymentMethods()),
                requestDTO.softDescriptor(),
                requestDTO.redirectUrl(),
                requestDTO.returnUrl(),
                requestDTO.notificationUrls(),
                requestDTO.paymentNotificationUrls()
        );
    }

    public static ResCheckoutDto toCheckoutResponseDTO(Checkout checkout) {
        if (checkout == null) return null;
        return new ResCheckoutDto(
                checkout.getId() != null ? checkout.getId().toString() : null,
                checkout.getGatewayId(),
                checkout.getStatus(),
                checkout.getCreatedAt(),
                checkout.getExpirationDate(),
                CustomerMapper.toCustomerDTO(checkout.getCustomer()),
                ItemMapper.toItemDTOList(checkout.getItems()),
                PaymentMethodMapper.toPaymentMethodDTOList(checkout.getPaymentMethods()),
                checkout.getAdditionalAmount(),
                checkout.getDiscountAmount()
        );
    }
}
