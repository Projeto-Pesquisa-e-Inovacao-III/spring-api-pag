package com.csf.api_pag.application.gateway;

public interface WebhookGatewayValidator {
    void validate(String signature, String payload);
}
