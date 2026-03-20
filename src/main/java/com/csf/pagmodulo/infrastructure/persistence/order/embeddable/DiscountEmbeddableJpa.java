package com.csf.pagmodulo.infrastructure.persistence.order.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DiscountEmbeddableJpa {

    @Column(name = "discount_date", length = 10)
    private String date;

    @Column(name = "discount_value")
    private int value;

    protected DiscountEmbeddableJpa() {}

    public DiscountEmbeddableJpa(String date, int value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() { return date; }
    public int getValue() { return value; }
}

