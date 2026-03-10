package com.fillipe.pagmodulo.infrastructure.pagbank;

import com.fillipe.pagmodulo.infrastructure.web.webhook.exeception.WebhookAuthorizationException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PagBankValidateCertification  {
    public static void validate(String token, String payload, String target){
        String signature = token+"-"+payload;
        MessageDigest digest;

        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] encodedHash = digest.digest(signature.getBytes(StandardCharsets.UTF_8));

        byte[] targetBytes = target.getBytes(StandardCharsets.UTF_8);

        byte[] computedBytes = bytesToHex(encodedHash).getBytes(StandardCharsets.UTF_8);

        if (!MessageDigest.isEqual(computedBytes, targetBytes)) {
            throw new WebhookAuthorizationException("Assinatura invalida");
        }

    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
