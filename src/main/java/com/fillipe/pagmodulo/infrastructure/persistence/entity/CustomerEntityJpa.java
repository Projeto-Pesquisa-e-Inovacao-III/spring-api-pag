package com.fillipe.pagmodulo.infrastructure.persistence.entity;

import com.fillipe.pagmodulo.infrastructure.persistence.embeddable.PhoneEmbeddableJpa;
import com.fillipe.pagmodulo.infrastructure.persistence.embeddable.TaxDocumentEmbeddableJpa;
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

    public CustomerEntityJpa() {
    }

    public CustomerEntityJpa(Long id, String name, String email, TaxDocumentEmbeddableJpa taxDocument, PhoneEmbeddableJpa phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.taxDocument = taxDocument;
        this.phone = phone;
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
}
