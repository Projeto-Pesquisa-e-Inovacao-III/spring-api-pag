package com.csf.api_pag.infrastructure.persistence.checkout.embeddable;

import com.csf.api_pag.infrastructure.persistence.checkout.embeddable.enums.PaymentTypeJpa;
import jakarta.persistence.*;

@Embeddable
public class PaymentMethodEmbeddableJpa {

    @Column(name = "payment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentTypeJpa type;

    protected PaymentMethodEmbeddableJpa() {}

    public PaymentMethodEmbeddableJpa(PaymentTypeJpa type) {
        this.type = type;
    }

    public PaymentTypeJpa getType() {
        return type;
    }
}
