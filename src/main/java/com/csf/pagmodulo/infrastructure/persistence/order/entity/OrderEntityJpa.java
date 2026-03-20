package com.csf.pagmodulo.infrastructure.persistence.order.entity;

import com.csf.pagmodulo.infrastructure.persistence.shared.embeddable.CustomerEmbeddableJpa;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order_entity")
public class OrderEntityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @Column(nullable = false, updatable = false)
    private UUID checkoutId;

    @Column(unique = true, nullable = false, updatable = false)
    private String gatewayOrderId;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Embedded
    private CustomerEmbeddableJpa customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChargeEntityJpa> charges;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntityJpa> itens;

    public OrderEntityJpa() {}

    public OrderEntityJpa(Long id, UUID uuid, UUID checkoutId, String gatewayOrderId, OffsetDateTime createdAt, CustomerEmbeddableJpa customer, List<ChargeEntityJpa> charges, List<OrderItemEntityJpa> itens) {
        this.id = id;
        this.uuid = uuid;
        this.checkoutId = checkoutId;
        this.gatewayOrderId = gatewayOrderId;
        this.createdAt = createdAt;
        this.customer = customer;
        this.charges = charges;
        this.itens = itens;
    }

    public Long getId() { return id; }
    public UUID getUuid() { return uuid; }
    public UUID getCheckoutId() { return checkoutId; }
    public String getGatewayOrderId() { return gatewayOrderId; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public CustomerEmbeddableJpa getCustomer() { return customer; }
    public List<ChargeEntityJpa> getCharges() { return charges; }
    public List<OrderItemEntityJpa> getItens() { return itens; }

    public void setId(Long id) { this.id = id; }
    public void setUuid(UUID uuid) { this.uuid = uuid; }
    public void setCheckoutId(UUID checkoutId) { this.checkoutId = checkoutId; }
    public void setGatewayOrderId(String gatewayOrderId) { this.gatewayOrderId = gatewayOrderId; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public void setCustomer(CustomerEmbeddableJpa customer) { this.customer = customer; }
    public void setCharges(List<ChargeEntityJpa> charges) { this.charges = charges; }
    public void setItens(List<OrderItemEntityJpa> itens) { this.itens = itens; }
}
