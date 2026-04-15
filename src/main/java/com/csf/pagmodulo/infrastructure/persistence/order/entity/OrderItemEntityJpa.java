package com.csf.pagmodulo.infrastructure.persistence.order.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItemEntityJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalItemId;
    private String name;
    private String description;
    private Integer quantity;
    private Integer unitAmount;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntityJpa order;

    public OrderItemEntityJpa() { }

    public OrderItemEntityJpa(Long id, String externalItemId, String name, String description, Integer quantity, Integer unitAmount, String imageUrl, OrderEntityJpa order) {
        this.id = id;
        this.externalItemId = externalItemId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unitAmount = unitAmount;
        this.imageUrl = imageUrl;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public String getExternalItemId() { return externalItemId; }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getUnitAmount() {
        return unitAmount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OrderEntityJpa getOrder() { return order; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setExternalItemId(String externalItemId) { this.externalItemId = externalItemId; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUnitAmount(Integer unitAmount) {
        this.unitAmount = unitAmount;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setOrder(OrderEntityJpa order) { this.order = order; }
}
