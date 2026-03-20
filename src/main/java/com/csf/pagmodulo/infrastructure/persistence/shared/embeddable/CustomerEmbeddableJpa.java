package com.csf.pagmodulo.infrastructure.persistence.shared.embeddable;

import jakarta.persistence.*;

@Embeddable
public class CustomerEmbeddableJpa {

    @Column(name = "customer_external_id")
    private String externalCustomerId;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_email")
    private String email;

    @Embedded
    private TaxDocumentEmbeddableJpa taxDocument;

    @Embedded
    private PhoneEmbeddableJpa phone;

    public CustomerEmbeddableJpa() {
    }

    public CustomerEmbeddableJpa(String externalCustomerId, String name, String email, TaxDocumentEmbeddableJpa taxDocument, PhoneEmbeddableJpa phone) {
        this.externalCustomerId = externalCustomerId;
        this.name = name;
        this.email = email;
        this.taxDocument = taxDocument;
        this.phone = phone;
    }

    public String getExternalCustomerId() {
        return externalCustomerId;
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

    public void setExternalCustomerId(String externalCustomerId) {
        this.externalCustomerId = externalCustomerId;
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

    @Override
    public String toString() {
        return "CustomerEmbeddableJpa{" +
                "externalCustomerId='" + externalCustomerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", taxDocument=" + taxDocument +
                ", phone=" + phone +
                '}';
    }
}






