package com.csf.api_pag.infrastructure.event.rabbit;

import com.csf.api_pag.domain.order.event.OrderPaidEvent;
import com.csf.api_pag.domain.shared.event.DomainEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.StringJoiner;

@Component
public class HmacService {
    private final String hmacSecret;

    public HmacService(@Value("${spring.rabbitmq.hmac.secret}") String hmacSecret) {
        this.hmacSecret = hmacSecret;
    }

    public String generateForEvent(DomainEvent domainEvent) {
        if (domainEvent instanceof OrderPaidEvent orderPaidEvent) {
            return generateFor(orderPaidEvent);
        }
        return hmacSha256(domainEvent.toString(), hmacSecret);
    }

    @Deprecated
    // RETIRAR
    public String generateFor(OrderPaidEvent orderPaidEvent) {
        String payload = new StringJoiner("|")
                .add("orderId=" + orderPaidEvent.orderId().value())
                .add("checkoutId=" + orderPaidEvent.checkoutId().value())
                .add("gatewayOrderId=" + orderPaidEvent.gatewayOrderId())
                .add("customerId=" + orderPaidEvent.customerId())
                .add("itensId=" + orderPaidEvent.itensId())
                .add("chargeId=" + orderPaidEvent.chargeId().value())
                .toString();
        return hmacSha256(payload, hmacSecret);
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