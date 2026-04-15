package com.csf.pagmodulo.infrastructure.persistence.order.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FineEmbeddableJpa {

    @Column(name = "fine_date", length = 10)
    private String date;

    @Column(name = "fine_value")
    private int value;

    protected FineEmbeddableJpa() {}

    public FineEmbeddableJpa(String date, int value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() { return date; }
    public int getValue() { return value; }
}

