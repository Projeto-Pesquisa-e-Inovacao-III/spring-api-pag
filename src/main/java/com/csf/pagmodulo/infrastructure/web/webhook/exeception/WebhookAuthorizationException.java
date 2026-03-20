package com.csf.pagmodulo.infrastructure.web.webhook.exeception;

public class WebhookAuthorizationException extends RuntimeException {
    public WebhookAuthorizationException(String message) {
        super(message);
    }
}
