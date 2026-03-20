package com.csf.pagmodulo.infrastructure.persistence.shared.embeddable;

import com.csf.pagmodulo.domain.shared.valueobjects.TaxDocumentType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class TaxDocumentEmbeddableJpa {
    @Column(name = "customer_tax_value")
    private String value;

    @Column(name = "customer_tax_type")
    @Enumerated(EnumType.STRING)
    private TaxDocumentType type;

    public TaxDocumentEmbeddableJpa() {
    }

    public TaxDocumentEmbeddableJpa(String value, TaxDocumentType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public TaxDocumentType getType() {
        return type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(TaxDocumentType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TaxDocumentEmbeddableJpa{" +
                "value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
