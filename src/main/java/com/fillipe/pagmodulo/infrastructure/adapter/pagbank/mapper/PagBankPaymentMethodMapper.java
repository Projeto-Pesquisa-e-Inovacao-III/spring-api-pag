package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.mapper;

import com.fillipe.pagmodulo.domain.valueobject.PaymentConfigOption;
import com.fillipe.pagmodulo.domain.valueobject.PaymentMethod;
import com.fillipe.pagmodulo.domain.valueobject.PaymentType;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.request.ReqPagBankPaymentMethodDto;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response.ResPagBankConfigOptionDto;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response.ResPagBankPaymentMethodDto;
import com.fillipe.pagmodulo.infrastructure.common.mapper.PaymentMethodMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PaymentMethodMapper.class})
public abstract class PagBankPaymentMethodMapper {

    @Autowired
    protected PaymentMethodMapper paymentMethodMapper;

    public PaymentMethod toPaymentMethodDomain(ResPagBankPaymentMethodDto dto) {
        if (dto == null) return null;
        PaymentType type = paymentMethodMapper.toPaymentType(dto.type());
        return new PaymentMethod(type, List.of());
    }

    public abstract List<PaymentMethod> toPaymentMethodsDomain(List<ResPagBankPaymentMethodDto> listDto);

    @Mapping(source = "type", target = "type", qualifiedByName = "fromPaymentType")
    public abstract ReqPagBankPaymentMethodDto toReqPagBankPaymentMethodDto(PaymentMethod paymentMethod);

    // PaymentConfigOption
    public abstract PaymentConfigOption toPaymentConfigOptionDomain(ResPagBankConfigOptionDto dto);
    public abstract List<PaymentConfigOption> toPaymentConfigOptionsDomain(List<ResPagBankConfigOptionDto> listDto);

    public abstract ResPagBankConfigOptionDto toResPagBankConfigOptionDto(PaymentConfigOption paymentConfigOption);
    public abstract List<ResPagBankConfigOptionDto> toResPagBankConfigOptionsDto(List<PaymentConfigOption> paymentConfigOptions);
}
