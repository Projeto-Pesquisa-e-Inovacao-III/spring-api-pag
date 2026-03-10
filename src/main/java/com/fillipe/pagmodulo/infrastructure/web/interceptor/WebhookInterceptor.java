package com.fillipe.pagmodulo.infrastructure.web.interceptor;

import com.fillipe.pagmodulo.application.gateway.WebhookGatewayValidator;
import com.fillipe.pagmodulo.infrastructure.web.webhook.exeception.WebhookAuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;

@Component
public class WebhookInterceptor implements HandlerInterceptor {

    private final WebhookGatewayValidator validator;

    public WebhookInterceptor(WebhookGatewayValidator validator) {
        this.validator = validator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

        String signature = request.getHeader("x-authenticity-token");
        if (signature == null || signature.isBlank()) {
            throw new WebhookAuthorizationException("Assinatura ausente");
        }

        String payload = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        validator.validate(signature, payload);
        return true;
    }
}
