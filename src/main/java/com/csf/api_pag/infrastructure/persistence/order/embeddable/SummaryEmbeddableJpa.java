package com.csf.api_pag.infrastructure.persistence.order.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SummaryEmbeddableJpa {

    @Column(name = "summary_total", nullable = false)
    private int total;

    @Column(name = "summary_paid", nullable = false)
    private int paid;

    @Column(name = "summary_refunded", nullable = false)
    private int refunded;

    protected SummaryEmbeddableJpa() {}

    public SummaryEmbeddableJpa(int total, int paid, int refunded) {
        this.total = total;
        this.paid = paid;
        this.refunded = refunded;
    }

    public int getTotal() { return total; }
    public int getPaid() { return paid; }
    public int getRefunded() { return refunded; }
}

