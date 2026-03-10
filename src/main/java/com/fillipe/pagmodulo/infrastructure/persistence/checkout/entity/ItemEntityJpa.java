package com.fillipe.pagmodulo.infrastructure.persistence.checkout.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class ItemEntityJpa {
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
    @JoinColumn(name = "checkout_id", nullable = false)
    CheckoutEntityJpa checkout;

    public ItemEntityJpa() { }

    public ItemEntityJpa(Long id, String externalItemId, String name, String description, Integer quantity, Integer unitAmount, String imageUrl, CheckoutEntityJpa checkout) {
        this.id = id;
        this.externalItemId = externalItemId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unitAmount = unitAmount;
        this.imageUrl = imageUrl;
        this.checkout = checkout;
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

    public CheckoutEntityJpa getCheckout() { return checkout; }

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

    public void setCheckout(CheckoutEntityJpa checkout) { this.checkout = checkout; }
}
