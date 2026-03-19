package com.fillipe.pagmodulo.presentation.mapper.checkout;

import com.fillipe.pagmodulo.application.dto.checkout.request.ReqCreateCheckoutDto;
import com.fillipe.pagmodulo.application.dto.checkout.response.ResCheckoutCreatedDto;
import com.fillipe.pagmodulo.application.dto.checkout.response.ResCheckoutDto;
import com.fillipe.pagmodulo.presentation.mapper.shared.CustomerMapper;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutCommand;
import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;

public class CheckoutMapper {

    private CheckoutMapper() {}

    public static CreateCheckoutCommand toCreateCheckoutCommand(ReqCreateCheckoutDto requestDTO) {
        if (requestDTO == null) return null;
        return new CreateCheckoutCommand(
                CustomerMapper.toCustomer(requestDTO.customer()),
                ItemMapper.toItemList(requestDTO.items()),
                requestDTO.additionalAmount(),
                requestDTO.discountAmount()
        );
    }

    public static ResCheckoutCreatedDto toResCheckoutCreatedDto(Checkout checkout, String payLink) {
        if (checkout == null) return null;
        return new ResCheckoutCreatedDto(
                checkout.getId().toString(),
                checkout.getStatus().name(),
                checkout.getCreatedAt(),
                payLink
        );
    }

    public static ResCheckoutDto toCheckoutResponseDTO(Checkout checkout) {
        if (checkout == null) return null;
        return new ResCheckoutDto(
                checkout.getId().toString(),
                checkout.getGatewayId(),
                checkout.getStatus(),
                checkout.getCreatedAt(),
                checkout.getExpirationDate(),
                CustomerMapper.toCustomerDTO(checkout.getCustomer()),
                ItemMapper.toItemDTOList(checkout.getItems()),
                checkout.getAdditionalAmount(),
                checkout.getDiscountAmount()
        );
    }
}
