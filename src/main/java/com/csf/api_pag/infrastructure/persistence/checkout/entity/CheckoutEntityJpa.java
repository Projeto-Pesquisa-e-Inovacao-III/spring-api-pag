package com.csf.api_pag.infrastructure.persistence.checkout.entity;

import com.csf.api_pag.infrastructure.persistence.checkout.embeddable.PaymentConfigOptionEmbeddableJpa;
import com.csf.api_pag.infrastructure.persistence.checkout.embeddable.PaymentMethodEmbeddableJpa;
import com.csf.api_pag.infrastructure.persistence.checkout.entity.enums.CheckoutStatusJpa;
import com.csf.api_pag.infrastructure.persistence.shared.embeddable.CustomerEmbeddableJpa;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "checkout")
public class CheckoutEntityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @Column(unique = true)
    private String gatewayId;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime expirationDate;

    @Column
    private OffsetDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CheckoutStatusJpa status;

    @Embedded
    private CustomerEmbeddableJpa customer;

    @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CheckoutItemEntityJpa> items;

    @Column(updatable = false)
    private Integer additionalAmount;

    @Column(updatable = false)
    private Integer discountAmount;

    @ElementCollection
    @CollectionTable(
            name = "checkout_payment_methods",
            joinColumns = @JoinColumn(name = "checkout_id")
    )
    private List<PaymentMethodEmbeddableJpa> paymentMethods;

    @ElementCollection
    @CollectionTable(
            name = "checkout_payment_config_options",
            joinColumns = @JoinColumn(name = "checkout_id")
    )
    private List<PaymentConfigOptionEmbeddableJpa> configOptions;

    public CheckoutEntityJpa() {}

    public CheckoutEntityJpa(
            UUID uuid,
            String gatewayId,
            OffsetDateTime expirationDate,
            OffsetDateTime createdAt,
            CheckoutStatusJpa status,
            CustomerEmbeddableJpa customer,
            List<CheckoutItemEntityJpa> items,
            Integer additionalAmount,
            Integer discountAmount,
            List<PaymentMethodEmbeddableJpa> paymentMethods,
            List<PaymentConfigOptionEmbeddableJpa> configOptions
    ) {
        this.uuid = uuid;
        this.gatewayId = gatewayId;
        this.expirationDate = expirationDate;
        this.createdAt = createdAt;
        this.status = status;
        this.customer = customer;
        this.items = items;
        this.additionalAmount = additionalAmount;
        this.discountAmount = discountAmount;
        this.paymentMethods = paymentMethods;
        this.configOptions = configOptions;
    }

    public Long getId() { return id; }
    public UUID getUuid() { return uuid; }
    public String getGatewayId() { return gatewayId; }
    public OffsetDateTime getExpirationDate() { return expirationDate; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public CheckoutStatusJpa getStatus() { return status; }
    public CustomerEmbeddableJpa getCustomer() { return customer; }
    public List<CheckoutItemEntityJpa> getItems() { return items; }
    public Integer getAdditionalAmount() { return additionalAmount; }
    public Integer getDiscountAmount() { return discountAmount; }
    public List<PaymentMethodEmbeddableJpa> getPaymentMethods() { return paymentMethods; }
    public List<PaymentConfigOptionEmbeddableJpa> getConfigOptions() { return configOptions; }

    public void setGatewayId(String gatewayId) { this.gatewayId = gatewayId; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public void setStatus(CheckoutStatusJpa status) { this.status = status; }
    public void setCustomer(CustomerEmbeddableJpa customer) { this.customer = customer; }
    public void setItems(List<CheckoutItemEntityJpa> items) { this.items = items; }
    public void setId(Long id) { this.id = id; }
    public void setUuid(UUID uuid) { this.uuid = uuid; }
    public void setExpirationDate(OffsetDateTime expirationDate) { this.expirationDate = expirationDate; }
    public void setAdditionalAmount(Integer additionalAmount) { this.additionalAmount = additionalAmount; }
    public void setDiscountAmount(Integer discountAmount) { this.discountAmount = discountAmount; }
    public void setPaymentMethods(List<PaymentMethodEmbeddableJpa> paymentMethods) { this.paymentMethods = paymentMethods; }
    public void setConfigOptions(List<PaymentConfigOptionEmbeddableJpa> configOptions) { this.configOptions = configOptions; }
}