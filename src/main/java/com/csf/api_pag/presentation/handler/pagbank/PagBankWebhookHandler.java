package com.csf.api_pag.presentation.handler.pagbank;

import com.csf.api_pag.infrastructure.web.webhook.exeception.WebhookAuthorizationException;
import com.csf.api_pag.presentation.shared.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PagBankWebhookHandler {

    private static final Logger log = LoggerFactory.getLogger(PagBankWebhookHandler.class);

    @ExceptionHandler(WebhookAuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleWebhookAuthorization(WebhookAuthorizationException ex) {
        log.warn("Webhook não autorizado: {}", ex.getMessage());
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.UNAUTHORIZED.value(), "Não Autorizado", ex.getMessage())
                .retryable(false)
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }
}
