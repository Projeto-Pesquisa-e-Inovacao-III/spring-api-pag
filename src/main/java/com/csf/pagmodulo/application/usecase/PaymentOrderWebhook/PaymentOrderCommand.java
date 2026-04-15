package com.csf.pagmodulo.application.usecase.PaymentOrderWebhook;

import com.csf.pagmodulo.domain.order.enums.BillingStatus;
import com.csf.pagmodulo.domain.order.valueobject.Amount;
import com.csf.pagmodulo.domain.order.valueobject.PaymentResponse;
import com.csf.pagmodulo.domain.order.valueobject.paymentInstruction.PaymentInstruction;
import com.csf.pagmodulo.domain.shared.valueobjects.ChargeId;
import com.csf.pagmodulo.domain.shared.valueobjects.CheckoutId;
import com.csf.pagmodulo.domain.shared.valueobjects.Customer;
import com.csf.pagmodulo.domain.shared.valueobjects.Item;
import com.csf.pagmodulo.domain.shared.valueobjects.OrderId;

import java.time.OffsetDateTime;
import java.util.List;

public record PaymentOrderCommand(
        OrderId orderId,
        CheckoutId checkoutId,
        String gatewayOrderId,
        OffsetDateTime createdAt,
        Customer customer,
        List<Item> items,
        List<ChargeCommand> charges
) {

    public record ChargeCommand(
            ChargeId id,
            String gatewayId,
            BillingStatus status,
            OffsetDateTime createdAt,
            OffsetDateTime paidAt,
            String description,
            Amount amount,
            PaymentResponse paymentResponse,
            PaymentInstruction paymentInstruction
    ) {}
}


