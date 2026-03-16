package com.fillipe.pagmodulo.domain.order.valueobject;

import com.fillipe.pagmodulo.domain.shared.exceptions.InvalidFieldException;

public record PaymentResponse(
        Integer code,
        String message,
        String reference,
        RawData rawData
) {
    public PaymentResponse {
        validateCode(code);
        validateMessage(message);
        validateReference(reference);
    }

    private static void validateCode(Integer code) {
        if (code == null) throw new InvalidFieldException("PaymentResponse.code", "code não pode ser nulo");
        String codeStr = String.valueOf(code);
        if (codeStr.length() != 5) throw new InvalidFieldException("PaymentResponse.code", "code deve ter exatamente 5 caracteres");
    }

    private static void validateMessage(String message) {
        if (message == null || message.isBlank()) throw new InvalidFieldException("PaymentResponse.message", "message não pode ser nulo ou em branco");
        if (message.length() < 5 || message.length() > 100) throw new InvalidFieldException("PaymentResponse.message", "message deve ter entre 5 e 100 caracteres");
    }

    private static void validateReference(String reference) {
        if (reference != null && (reference.length() < 4 || reference.length() > 20)) throw new InvalidFieldException("PaymentResponse.reference", "reference deve ter entre 4 e 20 caracteres");
    }
}
