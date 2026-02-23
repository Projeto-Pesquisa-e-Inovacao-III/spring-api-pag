package com.fillipe.pagmodulo.infrastructure.persistence.embeddable;

import com.fillipe.pagmodulo.domain.valueobject.TaxDocumentType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TaxDocumentEmbeddableJpa {
    @Column(name = "tax_value")
    private String value;
    @Column(name = "tax_type")
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
}
