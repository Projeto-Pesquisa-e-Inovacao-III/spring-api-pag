package com.csf.pagmodulo.infrastructure.persistence.order.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class PaymentResponseEmbeddableJpa {

    @Column(name = "payment_response_code")
    private Integer code;

    @Column(name = "payment_response_message", length = 100)
    private String message;

    @Column(name = "payment_response_reference", length = 20)
    private String reference;

    @Embedded
    private RawDataEmbeddableJpa rawData;

    protected PaymentResponseEmbeddableJpa() {}

    public PaymentResponseEmbeddableJpa(Integer code, String message, String reference, RawDataEmbeddableJpa rawData) {
        this.code = code;
        this.message = message;
        this.reference = reference;
        this.rawData = rawData;
    }

    public Integer getCode() { return code; }
    public String getMessage() { return message; }
    public String getReference() { return reference; }
    public RawDataEmbeddableJpa getRawData() { return rawData; }
}

