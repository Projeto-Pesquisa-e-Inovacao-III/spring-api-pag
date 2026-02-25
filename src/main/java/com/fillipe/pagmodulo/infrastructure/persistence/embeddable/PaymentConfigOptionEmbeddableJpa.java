package com.fillipe.pagmodulo.infrastructure.persistence.embeddable;

import com.fillipe.pagmodulo.domain.checkout.valueobject.PaymentConfigOptionType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class PaymentConfigOptionEmbeddableJpa {

    @Column(name = "config_type")
    @Enumerated(EnumType.STRING)
    private PaymentConfigOptionType type;

    @Column(name = "config_value")
    private String value;

    public PaymentConfigOptionEmbeddableJpa() {
    }

    public PaymentConfigOptionEmbeddableJpa(PaymentConfigOptionType type, String value) {
        this.type = type;
        this.value = value;
    }

    public PaymentConfigOptionType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setType(PaymentConfigOptionType type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
