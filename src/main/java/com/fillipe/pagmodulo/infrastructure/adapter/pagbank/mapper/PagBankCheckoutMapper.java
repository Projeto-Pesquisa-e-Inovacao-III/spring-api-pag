package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.mapper;

import com.fillipe.pagmodulo.domain.entity.Checkout;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.request.ReqPagBankCheckoutRegisterDto;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response.ResPagBankCheckoutRegisterDto;
import com.fillipe.pagmodulo.infrastructure.common.mapper.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",
        uses = {
                ConversionMapper.class,
                CheckoutMapper.class,
                PagBankPaymentMethodMapper.class,
                PagBankCustomerMapper.class,
                PagBankItemMapper.class,
                PagBankLinkMapper.class,
                PagBankPaymentMethodMapper.class
        })
public abstract class PagBankCheckoutMapper {

    @Autowired
    protected CheckoutMapper checkoutMapper;
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
    public abstract ReqPagBankCheckoutRegisterDto toReqPagBankCheckoutRegisterDto(Checkout checkout);

    @ObjectFactory
    public Checkout toDomain(ResPagBankCheckoutRegisterDto dto){
        return new Checkout(
                conversionMapper.toUUID(dto.referenceId()),
                dto.id(),
                OffsetDateTime.parse(dto.expirationDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                OffsetDateTime.parse(dto.createdAt(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                checkoutMapper.toStatus(dto.status()),
                pagBankCustomerMapper.toCustomerDomain(dto.customer()),
                dto.customerModifiable(),
                pagBankItemMapper.toItemsDomain(dto.items()),
                dto.additionalAmount(),
                dto.discountAmount(),
                pagBankPaymentMethodMapper.toPaymentMethodsDomain(dto.paymentMethods()),
                dto.softDescriptor(),
                dto.redirectUrl(),
                dto.returnUrl(),
                dto.notificationUrls(),
                dto.paymentNotificationUrls(),
                pagBankLinkMapper.toLinksDomain(dto.links())
        );
    }
}
