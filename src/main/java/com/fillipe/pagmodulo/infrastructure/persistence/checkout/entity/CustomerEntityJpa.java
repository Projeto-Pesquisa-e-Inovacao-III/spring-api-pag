package com.fillipe.pagmodulo.infrastructure.persistence.checkout.entity;

import com.fillipe.pagmodulo.infrastructure.persistence.checkout.embeddable.PhoneEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.checkout.embeddable.TaxDocumentEmbeddableJpa;
import jakarta.persistence.*;

@Entity
@Table(name="customer")
public class CustomerEntityJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Embedded
    @Column(name = "tax_document")
    private TaxDocumentEmbeddableJpa taxDocument;

    @Embedded
    private PhoneEmbeddableJpa phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkout_id", nullable = false)
    private CheckoutEntityJpa checkout;

    public CustomerEntityJpa() {
    }

    public CustomerEntityJpa(Long id, String name, String email, TaxDocumentEmbeddableJpa taxDocument, PhoneEmbeddableJpa phone, CheckoutEntityJpa checkout) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.taxDocument = taxDocument;
        this.phone = phone;
        this.checkout = checkout;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public CheckoutEntityJpa getCheckout() { return checkout; }
    public TaxDocumentEmbeddableJpa getTaxDocument() {
        return taxDocument;
    }
    public PhoneEmbeddableJpa getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTaxDocument(TaxDocumentEmbeddableJpa taxDocument) {
        this.taxDocument = taxDocument;
    }
    public void setPhone(PhoneEmbeddableJpa phone) {
        this.phone = phone;
    }
    public void setCheckout(CheckoutEntityJpa checkout) { this.checkout = checkout; }

    @Override
    public String toString() {
        return "CustomerEntityJpa{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", taxDocument=" + taxDocument +
                ", phone=" + phone +
                ", checkout=" + checkout.getGatewayId() +
                '}';
    }
}
