package com.csf.api_pag.infrastructure.persistence.checkout.embeddable;

import com.csf.api_pag.infrastructure.persistence.checkout.embeddable.enums.PaymentConfigOptionTypeJpa;
import com.csf.api_pag.infrastructure.persistence.checkout.embeddable.enums.PaymentTypeJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class PaymentConfigOptionEmbeddableJpa {

    @Column(name = "payment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentTypeJpa paymentType;

    @Column(name = "config_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentConfigOptionTypeJpa type;

    @Column(name = "config_value", nullable = false)
    private int value;

    protected PaymentConfigOptionEmbeddableJpa() {}

    public PaymentConfigOptionEmbeddableJpa(PaymentTypeJpa paymentType,
                                            PaymentConfigOptionTypeJpa type,
                                            int value) {
        this.paymentType = paymentType;
        this.type = type;
        this.value = value;
    }

    public PaymentTypeJpa getPaymentType() { return paymentType; }

    public PaymentConfigOptionTypeJpa getType() { return type; }

    public int getValue() { return value; }
}