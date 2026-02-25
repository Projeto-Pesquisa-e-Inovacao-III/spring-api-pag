package com.fillipe.pagmodulo.infrastructure.pagbank.mapper;

import com.fillipe.pagmodulo.domain.entity.Checkout;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.request.ReqPagBankCheckoutDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.response.ResPagBankCheckoutDto;
import com.fillipe.pagmodulo.infrastructure.common.mapper.*;
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
                PagBankLinkMapper.class,
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
    private PagBankLinkMapper pagBankLinkMapper;
    @Autowired
    private ConversionMapper conversionMapper;


    @Mapping(source = "uuid", target = "referenceId")
    @Mapping(source = "expirationDate", target = "expirationDate", qualifiedByName = "fromOffsetDateTime")
    public abstract ReqPagBankCheckoutDto toReqPagBankCheckoutRegisterDto(Checkout checkout);

    @ObjectFactory
    public Checkout toDomain(ResPagBankCheckoutDto dto){
        return Checkout.fromExisting()
                .uuid(conversionMapper.toUUID(dto.referenceId()))
                .gatewayId(dto.id())
                .expirationDate(OffsetDateTime.parse(dto.expirationDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .createdAt(OffsetDateTime.parse(dto.createdAt(), DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .status(utilCheckoutMapper.toStatus(dto.status()))
                .customer(pagBankCustomerMapper.toCustomerDomain(dto.customer()))
                .customerModifiable(dto.customerModifiable())
                .items(pagBankItemMapper.toItemsDomain(dto.items()))
                .additionalAmount(dto.additionalAmount())
                .discountAmount(dto.discountAmount())
                .paymentMethods(pagBankPaymentMethodMapper.toPaymentMethodsDomain(dto.paymentMethods()))
                .softDescriptor(dto.softDescriptor())
                .redirectUrl(dto.redirectUrl())
                .returnUrl(dto.returnUrl())
                .notificationUrls(dto.notificationUrls())
                .paymentNotificationUrls(dto.paymentNotificationUrls())
                .links(pagBankLinkMapper.toLinksDomain(dto.links()))
                .build();
    }
}
