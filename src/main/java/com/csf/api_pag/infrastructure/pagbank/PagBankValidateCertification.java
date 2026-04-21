package com.csf.api_pag.infrastructure.pagbank;

import com.csf.api_pag.infrastructure.web.webhook.exeception.WebhookAuthorizationException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class PagBankValidateCertification {
    private PagBankValidateCertification() {
        /* This utility class should not be instantiated */
    }
    public static void validate(String token, String payload, String target) {
        if (token == null || payload == null || target == null) {
            throw new WebhookAuthorizationException("Dados de validação incompletos");
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            digest.update(token.getBytes(StandardCharsets.UTF_8));
            digest.update((byte) '-');
            digest.update(payload.getBytes(StandardCharsets.UTF_8));

            byte[] computedHash = digest.digest();
            byte[] targetHash = HexFormat.of().parseHex(target);

            if (!MessageDigest.isEqual(computedHash, targetHash)) {
                throw new WebhookAuthorizationException("Assinatura invalida");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Algoritmo de hash não encontrado", e);
        } catch (IllegalArgumentException e) {
            throw new WebhookAuthorizationException("Formato de assinatura invalido");
        }
    }
}
