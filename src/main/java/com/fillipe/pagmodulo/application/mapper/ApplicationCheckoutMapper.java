package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.request.ReqCreateCheckoutDto;
import com.fillipe.pagmodulo.application.dto.response.ResCheckoutDto;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutCommand;
import com.fillipe.pagmodulo.domain.entity.Checkout;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {
        CustomerMapper.class,
        ItemMapper.class,
        PaymentMethodMapper.class,
        PaymentConfigOptionMapper.class,
        PhoneMapper.class,
        TaxDocumentMapper.class,
        LinkMapper.class
}, imports = LocalDateTime.class)
public interface ApplicationCheckoutMapper {

    @Mapping(target = "expirationDate", expression = "java(LocalDateTime.now().plusHours(1))")
    CreateCheckoutCommand toCreateCheckoutCommand(ReqCreateCheckoutDto requestDTO);

    @Mapping(source = "uuid", target = "id")
    @Mapping(source = "gatewayId", target = "externalCheckoutId")
    ResCheckoutDto toCheckoutResponseDTO(Checkout checkout);
}

