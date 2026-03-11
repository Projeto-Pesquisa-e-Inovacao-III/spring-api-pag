package com.fillipe.pagmodulo.infrastructure.persistence.order.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class AmountEmbeddableJpa {

    @Column(name = "amount_value", nullable = false)
    private int moneyValue;

    @Column(name = "amount_currency", nullable = false, length = 3)
    private String moneyCurrency;

    @Embedded
    private SummaryEmbeddableJpa summary;

    protected AmountEmbeddableJpa() {}

    public AmountEmbeddableJpa(int moneyValue, String moneyCurrency, SummaryEmbeddableJpa summary) {
        this.moneyValue = moneyValue;
        this.moneyCurrency = moneyCurrency;
        this.summary = summary;
    }

    public int getMoneyValue() { return moneyValue; }
    public String getMoneyCurrency() { return moneyCurrency; }
    public SummaryEmbeddableJpa getSummary() { return summary; }
}

