package com.csf.pagmodulo.presentation.mapper.order;

import com.csf.pagmodulo.application.dto.order.request.ReqAmountDto;
import com.csf.pagmodulo.application.dto.order.request.ReqChargeDto;
import com.csf.pagmodulo.application.dto.order.request.ReqCustomerOrderDto;
import com.csf.pagmodulo.application.dto.order.request.ReqItemOrderDto;
import com.csf.pagmodulo.application.dto.order.request.ReqOrderDto;
import com.csf.pagmodulo.application.dto.order.request.ReqPaymentResponseDto;
import com.csf.pagmodulo.application.dto.shared.CustomerDto;
import com.csf.pagmodulo.application.dto.shared.ItemDto;
import com.csf.pagmodulo.application.dto.shared.PhoneDto;
import com.csf.pagmodulo.application.dto.shared.TaxDocumentDto;
import com.csf.pagmodulo.application.usecase.PaymentOrderWebhook.PaymentOrderCommand;
import com.csf.pagmodulo.domain.order.enums.BillingStatus;
import com.csf.pagmodulo.domain.order.valueobject.Amount;
import com.csf.pagmodulo.domain.order.valueobject.PaymentResponse;
import com.csf.pagmodulo.domain.order.valueobject.Summary;
import com.csf.pagmodulo.domain.shared.valueobjects.ChargeId;
import com.csf.pagmodulo.domain.shared.valueobjects.CheckoutId;
import com.csf.pagmodulo.domain.shared.valueobjects.Customer;
import com.csf.pagmodulo.domain.shared.valueobjects.Item;
import com.csf.pagmodulo.domain.shared.valueobjects.Money;
import com.csf.pagmodulo.domain.shared.valueobjects.OrderId;
import com.csf.pagmodulo.domain.shared.valueobjects.TaxDocumentType;
import com.csf.pagmodulo.presentation.mapper.shared.CustomerMapper;
import com.csf.pagmodulo.presentation.mapper.shared.ItemMapper;

import java.util.List;
import java.util.UUID;

public class OrderMapper {

    private OrderMapper() {}

    public static PaymentOrderCommand toCommand(ReqOrderDto dto) {
        if (dto == null) return null;

        return new PaymentOrderCommand(
                new OrderId(extractUuid(dto.id())),
                new CheckoutId(UUID.fromString(dto.referenceId())),
                dto.id(),
                dto.createdAt(),
                toCustomer(dto.customer()),
                toItemList(dto.items()),
                toChargeCommandList(dto.charges())
        );
    }

    private static Customer toCustomer(ReqCustomerOrderDto dto) {
        return CustomerMapper.toCustomer(toCustomerDto(dto));
    }

    private static CustomerDto toCustomerDto(ReqCustomerOrderDto dto) {
        if (dto == null) return null;

        PhoneDto phoneDto = dto.phones() != null && !dto.phones().isEmpty()
                ? new PhoneDto(dto.phones().getFirst().country(), dto.phones().getFirst().area(), dto.phones().getFirst().number())
                : null;

        return new CustomerDto(
                null,
                dto.name(),
                dto.email(),
                new TaxDocumentDto(dto.taxId(), TaxDocumentType.CPF),
                phoneDto
        );
    }

    private static List<Item> toItemList(List<ReqItemOrderDto> items) {
        if (items == null) return List.of();
        return items.stream().map(OrderMapper::toItem).toList();
    }

    private static Item toItem(ReqItemOrderDto dto) {
        return ItemMapper.toItem(toItemDto(dto));
    }

    private static ItemDto toItemDto(ReqItemOrderDto dto) {
        if (dto == null) return null;
        return new ItemDto(dto.referenceId(), dto.name(), null, dto.quantity(), dto.unitAmount(), null);
    }

    private static List<PaymentOrderCommand.ChargeCommand> toChargeCommandList(List<ReqChargeDto> charges) {
        if (charges == null) return List.of();
        return charges.stream().map(OrderMapper::toChargeCommand).toList();
    }

    private static PaymentOrderCommand.ChargeCommand toChargeCommand(ReqChargeDto dto) {
        if (dto == null) return null;

        return new PaymentOrderCommand.ChargeCommand(
                new ChargeId(extractUuid(dto.id())),
                dto.id(),
                BillingStatus.valueOf(dto.status()),
                dto.createdAt(),
                dto.paidAt(),
                dto.description(),
                toAmount(dto.amount()),
                toPaymentResponse(dto.paymentResponse()),
                null
        );
    }

    private static Amount toAmount(ReqAmountDto dto) {
        if (dto == null) return null;
        return new Amount(
                new Money(dto.value(), dto.currency()),
                new Summary(dto.summary().total(), dto.summary().paid(), dto.summary().refunded())
        );
    }

    private static PaymentResponse toPaymentResponse(ReqPaymentResponseDto dto) {
        if (dto == null) return null;
        return new PaymentResponse(
                Integer.parseInt(dto.code()),
                dto.message(),
                dto.reference(),
                null
        );
    }

    private static UUID extractUuid(String rawId) {
        if (rawId == null) return null;
        int underscoreIndex = rawId.indexOf('_');
        String uuidPart = underscoreIndex >= 0 ? rawId.substring(underscoreIndex + 1) : rawId;
        return UUID.fromString(uuidPart);
    }
}
