package com.csf.api_pag.infrastructure.pagbank;

import com.csf.api_pag.application.gateway.WebhookGatewayValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PagBankSignatureValidator implements WebhookGatewayValidator {

    @Value("${pagbank.api.token}")
    private String token;

    @Override
    public void validate(String signature, String payload) {
        PagBankValidateCertification.validate(token, payload, signature);
    }
}
