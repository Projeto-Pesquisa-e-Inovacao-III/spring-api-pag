package com.fillipe.pagmodulo.infrastructure.pagbank.mapper;

import com.fillipe.pagmodulo.domain.checkout.valueobject.PaymentConfigOption;
import com.fillipe.pagmodulo.domain.checkout.valueobject.PaymentMethod;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.common.PagBankConfigOptionDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.common.PagBankPaymentMethodDto;
import com.fillipe.pagmodulo.infrastructure.common.mapper.UtilPaymentMethodMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UtilPaymentMethodMapper.class})
public abstract class PagBankPaymentMethodMapper {

    @Autowired
    protected UtilPaymentMethodMapper utilPaymentMethodMapper;

    public PaymentMethod toPaymentMethodDomain(PagBankPaymentMethodDto dto) {
        if (dto == null) return null;
        return new PaymentMethod(utilPaymentMethodMapper.toPaymentType(dto.type()));
    }

    public abstract List<PaymentMethod> toPaymentMethodsDomain(List<PagBankPaymentMethodDto> listDto);

    @Mapping(source = "type", target = "type", qualifiedByName = "fromPaymentType")
    public abstract PagBankPaymentMethodDto toPagBankPaymentMethodDto(PaymentMethod paymentMethod);

    // PaymentConfigOption
    public abstract PaymentConfigOption toPaymentConfigOptionDomain(PagBankConfigOptionDto dto);
    public abstract List<PaymentConfigOption> toPaymentConfigOptionsDomain(List<PagBankConfigOptionDto> listDto);

    public abstract PagBankConfigOptionDto toPagBankConfigOptionDto(PaymentConfigOption paymentConfigOption);
    public abstract List<PagBankConfigOptionDto> toPagBankConfigOptionsDto(List<PaymentConfigOption> paymentConfigOptions);
}
