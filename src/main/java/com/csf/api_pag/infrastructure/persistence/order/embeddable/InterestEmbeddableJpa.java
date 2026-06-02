package com.csf.api_pag.infrastructure.persistence.order.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class InterestEmbeddableJpa {

    @Column(name = "interest_date", length = 10)
    private String date;

    @Column(name = "interest_value")
    private int value;

    protected InterestEmbeddableJpa() {}

    public InterestEmbeddableJpa(String date, int value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() { return date; }
    public int getValue() { return value; }
}

