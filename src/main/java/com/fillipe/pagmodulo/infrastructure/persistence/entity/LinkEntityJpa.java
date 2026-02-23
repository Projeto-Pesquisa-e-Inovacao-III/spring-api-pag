package com.fillipe.pagmodulo.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "link")
public class LinkEntityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rel;
    private String href;
    private String method;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkout_id")
    CheckoutEntityJpa checkout;

    public LinkEntityJpa() {
    }

    public LinkEntityJpa(Long id, String rel, String href, String method, CheckoutEntityJpa checkout) {
        this.id = id;
        this.rel = rel;
        this.href = href;
        this.method = method;
        this.checkout = checkout;
    }

    public Long getId() {
        return id;
    }

    public String getRel() {
        return rel;
    }

    public String getHref() {
        return href;
    }

    public String getMethod() {
        return method;
    }

    public CheckoutEntityJpa getCheckout() { return checkout; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setCheckout(CheckoutEntityJpa checkout) { this.checkout = checkout; }
}
