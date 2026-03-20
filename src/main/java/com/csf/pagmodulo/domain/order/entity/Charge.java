package com.csf.pagmodulo.domain.order.entity;

import com.csf.pagmodulo.domain.order.enums.BillingStatus;
import com.csf.pagmodulo.domain.order.valueobject.Amount;
import com.csf.pagmodulo.domain.order.valueobject.PaymentResponse;
import com.csf.pagmodulo.domain.order.valueobject.paymentInstruction.PaymentInstruction;
import com.csf.pagmodulo.domain.shared.valueobjects.ChargeId;

import java.time.OffsetDateTime;

public class Charge {
    ChargeId id;
    String gatewayId;
    BillingStatus status;
    OffsetDateTime created_at;
    OffsetDateTime paid_at;
    String description;
    Amount amount;
    PaymentResponse paymentResponse;
    PaymentInstruction paymentInstruction;

    public Charge(ChargeId id, String gatewayId, BillingStatus status, OffsetDateTime created_at, OffsetDateTime paid_at, String description, Amount amount, PaymentResponse paymentResponse, PaymentInstruction paymentInstruction) {
        this.id = id;
        this.gatewayId = gatewayId;
        this.status = status;
        this.created_at = created_at;
        this.paid_at = paid_at;
        this.description = description;
        this.amount = amount;
        this.paymentResponse = paymentResponse;
        this.paymentInstruction = paymentInstruction;
    }

    public boolean isPaid() {
        return BillingStatus.PAID.equals(status);
    }

    public ChargeId getId() {
        return id;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public BillingStatus getStatus() {
        return status;
    }

    public OffsetDateTime getCreated_at() {
        return created_at;
    }

    public OffsetDateTime getPaid_at() {
        return paid_at;
    }

    public String getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    public PaymentResponse getPaymentResponse() {
        return paymentResponse;
    }

    public PaymentInstruction getPaymentInstruction() {
        return paymentInstruction;
    }
}