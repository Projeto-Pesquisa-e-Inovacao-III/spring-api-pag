package com.csf.api_pag.infrastructure.event.rabbit;

import com.csf.api_pag.domain.order.event.OrderPaidEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class HmacService {
    private final String hmacSecret;

    public HmacService(@Value("${spring.rabbitmq.hmac.secret}") String hmacSecret) {
        this.hmacSecret = hmacSecret;
    }

    public String generateFor(OrderPaidEvent orderPaidEvent) {
        String payload = buildIdempotencyPayload(orderPaidEvent);
        return hmacSha256(payload, hmacSecret);
    }

    private String buildIdempotencyPayload(OrderPaidEvent orderPaidEvent) {
        return String.join("|",
                "orderId=" + orderPaidEvent.orderId().value(),
                "checkoutId=" + orderPaidEvent.checkoutId().value(),
                "gatewayOrderId=" + orderPaidEvent.gatewayOrderId(),
                "customerId=" + orderPaidEvent.customerId(),
                "itensId=" + orderPaidEvent.itensId(),
                "chargeId=" + orderPaidEvent.chargeId().value()
        );
    }

    public String hmacSha256(String payload, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] digest = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(digest);
        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao gerar assinatura HMAC-SHA256", ex);
        }
    }
}
