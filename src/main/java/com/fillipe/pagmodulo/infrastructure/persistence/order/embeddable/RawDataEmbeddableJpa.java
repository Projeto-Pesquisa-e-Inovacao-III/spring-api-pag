package com.fillipe.pagmodulo.infrastructure.persistence.order.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RawDataEmbeddableJpa {

    @Column(name = "raw_data_authorization_code")
    private Integer authorizationCode;

    @Column(name = "raw_data_nsu", length = 20)
    private String nsu;

    @Column(name = "raw_data_reason_code", length = 20)
    private String reasonCode;

    @Column(name = "raw_data_merchant_advice_code", length = 20)
    private String merchantAdviceCode;

    protected RawDataEmbeddableJpa() {}

    public RawDataEmbeddableJpa(Integer authorizationCode, String nsu, String reasonCode, String merchantAdviceCode) {
        this.authorizationCode = authorizationCode;
        this.nsu = nsu;
        this.reasonCode = reasonCode;
        this.merchantAdviceCode = merchantAdviceCode;
    }

    public Integer getAuthorizationCode() { return authorizationCode; }
    public String getNsu() { return nsu; }
    public String getReasonCode() { return reasonCode; }
    public String getMerchantAdviceCode() { return merchantAdviceCode; }
}

