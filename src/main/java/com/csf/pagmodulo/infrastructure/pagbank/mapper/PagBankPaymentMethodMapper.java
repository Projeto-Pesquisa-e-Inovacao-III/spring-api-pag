package com.csf.pagmodulo.infrastructure.pagbank.mapper;

import com.csf.pagmodulo.domain.checkout.valueobject.paymentMethod.*;
import com.csf.pagmodulo.infrastructure.pagbank.dto.common.PagBankConfigOptionDto;
import com.csf.pagmodulo.infrastructure.pagbank.dto.common.PagBankPaymentMethodDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PagBankPaymentMethodMapper {

    default PaymentMethod toPaymentMethodDomain(PagBankPaymentMethodDto dto) {
        if (dto == null || dto.type() == null) return null;
        return switch (dto.type().toUpperCase()) {
            case "PIX" -> new PixPaymentMethod();
            case "BOLETO" -> new BoletoPaymentMethod();
            case "CREDIT_CARD" -> new CreditCardPaymentMethod();
            case "DEBIT_CARD" -> new DebitCardPaymentMethod();
            default -> null;
        };
    }

    default List<PaymentMethod> toPaymentMethodsDomain(List<PagBankPaymentMethodDto> listDto) {
        if (listDto == null) return Collections.emptyList();
        return listDto.stream()
                .map(this::toPaymentMethodDomain)
                .filter(java.util.Objects::nonNull)
                .toList();
    }

    default PagBankPaymentMethodDto toPagBankPaymentMethodDto(PaymentMethod paymentMethod) {
        if (paymentMethod == null) return null;
        String type = switch (paymentMethod) {
            case PixPaymentMethod ignored -> "PIX";
            case BoletoPaymentMethod ignored -> "BOLETO";
            case CreditCardPaymentMethod ignored -> "CREDIT_CARD";
            case DebitCardPaymentMethod ignored -> "DEBIT_CARD";
        };
        return new PagBankPaymentMethodDto(type);
    }

    default List<PagBankPaymentMethodDto> toPagBankPaymentMethodsDto(List<PaymentMethod> paymentMethods) {
        if (paymentMethods == null) return Collections.emptyList();
        return paymentMethods.stream()
                .map(this::toPagBankPaymentMethodDto)
                .filter(java.util.Objects::nonNull)
                .toList();
    }

    default PagBankPaymentMethodDto toDto(String type) {
        if (type == null) return null;
        return new PagBankPaymentMethodDto(type);
    }

    default List<PagBankPaymentMethodDto> toDtoList(List<String> types) {
        if (types == null) return Collections.emptyList();
        return types.stream()
                .map(this::toDto)
                .toList();
    }

    @Named("toPaymentConfigOptionDomain")
    default PaymentConfigOption toPaymentConfigOptionDomain(PagBankConfigOptionDto dto) {
        if (dto == null) return null;
        PaymentConfigOptionType optionType = PaymentConfigOptionType.valueOf(dto.option().toUpperCase());
        int value = Integer.parseInt(dto.value());
        return new PaymentConfigOption(optionType, value);
    }

    default List<PaymentConfigOption> toPaymentConfigOptionsDomain(List<PagBankConfigOptionDto> listDto) {
        if (listDto == null) return Collections.emptyList();
        return listDto.stream().map(this::toPaymentConfigOptionDomain).toList();
    }

    @Named("toPagBankConfigOptionDto")
    default PagBankConfigOptionDto toPagBankConfigOptionDto(PaymentConfigOption option) {
        if (option == null) return null;
        return new PagBankConfigOptionDto(option.option().name(), String.valueOf(option.value()));
    }

    default List<PagBankConfigOptionDto> toPagBankConfigOptionsDto(List<PaymentConfigOption> options) {
        if (options == null) return Collections.emptyList();
        return options.stream().map(this::toPagBankConfigOptionDto).toList();
    }
}
