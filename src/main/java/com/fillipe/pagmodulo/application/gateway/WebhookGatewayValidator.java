package com.fillipe.pagmodulo.application.gateway;

public interface WebhookGatewayValidator {
    void validate(String signature, String payload);
}
