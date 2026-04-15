package com.csf.api_pag.infrastructure.persistence.order.entity;

import com.csf.api_pag.infrastructure.persistence.order.embeddable.*;
import com.csf.api_pag.infrastructure.persistence.order.entity.enums.BillingStatusJpa;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "charge")
public class ChargeEntityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @Column(unique = true)
    private String gatewayId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingStatusJpa status;

    @Column(updatable = false)
    private OffsetDateTime createdAt;

    @Column
    private OffsetDateTime paidAt;

    @Column(length = 255)
    private String description;

    @Embedded
    private AmountEmbeddableJpa amount;

    @Embedded
    private PaymentResponseEmbeddableJpa paymentResponse;

    // PaymentInstruction: fine and interest as embedded, discounts as element collection
    @Embedded
    private FineEmbeddableJpa fine;

    @Embedded
    private InterestEmbeddableJpa interest;

    @ElementCollection
    @CollectionTable(
            name = "charge_discounts",
            joinColumns = @JoinColumn(name = "charge_id")
    )
    private List<DiscountEmbeddableJpa> discounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntityJpa order;

    public ChargeEntityJpa() {}

    public ChargeEntityJpa(
            UUID uuid,
            String gatewayId,
            BillingStatusJpa status,
            OffsetDateTime createdAt,
            OffsetDateTime paidAt,
            String description,
            AmountEmbeddableJpa amount,
            PaymentResponseEmbeddableJpa paymentResponse,
            FineEmbeddableJpa fine,
            InterestEmbeddableJpa interest,
            List<DiscountEmbeddableJpa> discounts,
            OrderEntityJpa order
    ) {
        this.uuid = uuid;
        this.gatewayId = gatewayId;
        this.status = status;
        this.createdAt = createdAt;
        this.paidAt = paidAt;
        this.description = description;
        this.amount = amount;
        this.paymentResponse = paymentResponse;
        this.fine = fine;
        this.interest = interest;
        this.discounts = discounts;
        this.order = order;
    }

    public Long getId() { return id; }
    public UUID getUuid() { return uuid; }
    public String getGatewayId() { return gatewayId; }
    public BillingStatusJpa getStatus() { return status; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public OffsetDateTime getPaidAt() { return paidAt; }
    public String getDescription() { return description; }
    public AmountEmbeddableJpa getAmount() { return amount; }
    public PaymentResponseEmbeddableJpa getPaymentResponse() { return paymentResponse; }
    public FineEmbeddableJpa getFine() { return fine; }
    public InterestEmbeddableJpa getInterest() { return interest; }
    public List<DiscountEmbeddableJpa> getDiscounts() { return discounts; }
    public OrderEntityJpa getOrder() { return order; }

    public void setId(Long id) { this.id = id; }
    public void setUuid(UUID uuid) { this.uuid = uuid; }
    public void setGatewayId(String gatewayId) { this.gatewayId = gatewayId; }
    public void setStatus(BillingStatusJpa status) { this.status = status; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public void setPaidAt(OffsetDateTime paidAt) { this.paidAt = paidAt; }
    public void setDescription(String description) { this.description = description; }
    public void setAmount(AmountEmbeddableJpa amount) { this.amount = amount; }
    public void setPaymentResponse(PaymentResponseEmbeddableJpa paymentResponse) { this.paymentResponse = paymentResponse; }
    public void setFine(FineEmbeddableJpa fine) { this.fine = fine; }
    public void setInterest(InterestEmbeddableJpa interest) { this.interest = interest; }
    public void setDiscounts(List<DiscountEmbeddableJpa> discounts) { this.discounts = discounts; }
    public void setOrder(OrderEntityJpa order) { this.order = order; }
}


