package com.fillipe.pagmodulo.infrastructure.pagbank.mapper;

import com.fillipe.pagmodulo.application.dto.checkout.external.ExtActivationCheckoutDto;
import com.fillipe.pagmodulo.application.dto.checkout.external.ExtInactivationCheckoutDto;
import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.common.PagBankConfigDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.request.ReqPagBankCheckoutDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.response.ResPagBankActivationCheckoutDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.response.ResPagBankCheckoutDto;
import com.fillipe.pagmodulo.infrastructure.common.mapper.*;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.response.ResPagBankInactivationCheckoutDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",
        uses = {
                ConversionMapper.class,
                UtilCheckoutMapper.class,
                PagBankPaymentMethodMapper.class,
                PagBankCustomerMapper.class,
                PagBankItemMapper.class,
                PagBankPaymentMethodMapper.class
        })
public abstract class PagBankCheckoutMapper {

    @Autowired
    protected UtilCheckoutMapper utilCheckoutMapper;
    @Autowired
    private PagBankCustomerMapper pagBankCustomerMapper;
    @Autowired
    private PagBankItemMapper pagBankItemMapper;
    @Autowired
    private PagBankPaymentMethodMapper pagBankPaymentMethodMapper;
    @Autowired
    private ConversionMapper conversionMapper;

    @Mapping(source = "checkout.id", target = "referenceId")
    @Mapping(source = "checkout.expirationDate", target = "expirationDate", qualifiedByName = "fromOffsetDateTime")
    @Mapping(source = "config.notificationWebhookUrls", target = "notificationUrls")
    @Mapping(source = "config.paymentNotificationUrls", target = "paymentNotificationUrls")
    @Mapping(source = "config.returnUrl", target = "returnUrl")
    @Mapping(source = "config.redirectUrl", target = "redirectUrl")
    @Mapping(source = "config.softDescriptor", target = "softDescriptor")
    @Mapping(source = "config.customerModifiable", target = "customerModifiable")
    @Mapping(source = "config.paymentMethod", target = "paymentMethods")
    public abstract ReqPagBankCheckoutDto toReqPagBankCheckoutRegisterDto(Checkout checkout, PagBankConfigDto config);

    @ObjectFactory
    public Checkout toDomain(ResPagBankCheckoutDto dto, String externalCustomerId) {
        return Checkout.fromExisting()
                .uuid(conversionMapper.toUUID(dto.referenceId()))
                .gatewayId(dto.id())
                .expirationDate(OffsetDateTime.parse(dto.expirationDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .createdAt(OffsetDateTime.parse(dto.createdAt(), DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .status(utilCheckoutMapper.toStatus(dto.status()))
                .customer(pagBankCustomerMapper.toCustomerDomain(dto.customer(), externalCustomerId))
                .items(pagBankItemMapper.toItemsDomain(dto.items()))
                .additionalAmount(dto.additionalAmount())
                .discountAmount(dto.discountAmount())
                .build();
    }

    // Activation
    public abstract ExtActivationCheckoutDto toResActivationDto(ResPagBankActivationCheckoutDto dto);

    // Inactivate
    public abstract ExtInactivationCheckoutDto toResInactivationCheckoutDto(ResPagBankInactivationCheckoutDto dto);
}
