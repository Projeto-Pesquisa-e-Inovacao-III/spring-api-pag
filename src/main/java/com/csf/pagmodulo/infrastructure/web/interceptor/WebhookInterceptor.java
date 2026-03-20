package com.csf.pagmodulo.infrastructure.web.interceptor;

import com.csf.pagmodulo.application.gateway.WebhookGatewayValidator;
import com.csf.pagmodulo.infrastructure.web.webhook.exeception.WebhookAuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;

@Component
@Profile("prod")
public class WebhookInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(WebhookInterceptor.class);

    private final WebhookGatewayValidator validator;

    public WebhookInterceptor(WebhookGatewayValidator validator) {
        this.validator = validator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        log.info("Requisição webhook recebida: method={}, uri={}", request.getMethod(), request.getRequestURI());

        String signature = request.getHeader("x-authenticity-token");
        if (signature == null || signature.isBlank()) {
            log.warn("Requisição webhook rejeitada: header x-authenticity-token ausente. uri={}", request.getRequestURI());
            throw new WebhookAuthorizationException("Assinatura ausente");
        }

        log.debug("Assinatura do webhook encontrada, lendo payload...");
        String payload = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        log.debug("Tamanho do payload do webhook: {} bytes", payload.length());

        validator.validate(signature, payload);

        log.info("Requisição webhook autorizada com sucesso. uri={}", request.getRequestURI());
        return true;
    }
}
