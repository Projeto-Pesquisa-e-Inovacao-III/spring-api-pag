package com.fillipe.pagmodulo.infrastructure.persistence.entity;

import com.fillipe.pagmodulo.domain.valueobject.PaymentType;
import com.fillipe.pagmodulo.infrastructure.persistence.embeddable.PaymentConfigOptionEmbeddableJpa;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "payment_method")
public class PaymentMethodEntityJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @ElementCollection
    @CollectionTable(name = "payment_config_options")
    private List<PaymentConfigOptionEmbeddableJpa> configOptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkout_id")
    CheckoutEntityJpa checkout;

    public PaymentMethodEntityJpa() {
    }

    public PaymentMethodEntityJpa(Long id, PaymentType type, List<PaymentConfigOptionEmbeddableJpa> configOptions, CheckoutEntityJpa checkout) {
        this.id = id;
        this.type = type;
        this.configOptions = configOptions;
        this.checkout = checkout;
    }

    public Long getId() {
        return id;
    }

    public PaymentType getType() {
        return type;
    }

    public List<PaymentConfigOptionEmbeddableJpa> getConfigOptions() {
        return configOptions;
    }

    public CheckoutEntityJpa getCheckout() { return checkout; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public void setConfigOptions(List<PaymentConfigOptionEmbeddableJpa> configOptions) { this.configOptions = configOptions; }

    public void setCheckout(CheckoutEntityJpa checkout) { this.checkout = checkout; }
}
