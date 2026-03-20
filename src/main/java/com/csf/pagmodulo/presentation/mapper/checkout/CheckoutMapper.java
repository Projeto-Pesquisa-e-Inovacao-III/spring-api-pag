package com.csf.pagmodulo.presentation.mapper.checkout;

import com.csf.pagmodulo.application.dto.checkout.request.ReqCreateCheckoutDto;
import com.csf.pagmodulo.application.dto.checkout.response.ResCheckoutCreatedDto;
import com.csf.pagmodulo.application.dto.checkout.response.ResCheckoutDto;
import com.csf.pagmodulo.presentation.mapper.shared.CustomerMapper;
import com.csf.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutCommand;
import com.csf.pagmodulo.domain.checkout.entity.Checkout;
import com.csf.pagmodulo.presentation.mapper.shared.ItemMapper;

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
