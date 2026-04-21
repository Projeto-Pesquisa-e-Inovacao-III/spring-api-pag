package com.csf.api_pag.application.usecase.PaymentOrderWebhook;

import com.csf.api_pag.domain.order.enums.BillingStatus;
import com.csf.api_pag.domain.order.valueobject.Amount;
import com.csf.api_pag.domain.order.valueobject.PaymentResponse;
import com.csf.api_pag.domain.order.valueobject.paymentInstruction.PaymentInstruction;
import com.csf.api_pag.domain.shared.valueobjects.ChargeId;
import com.csf.api_pag.domain.shared.valueobjects.CheckoutId;
import com.csf.api_pag.domain.shared.valueobjects.Customer;
import com.csf.api_pag.domain.shared.valueobjects.Item;
import com.csf.api_pag.domain.shared.valueobjects.OrderId;

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


