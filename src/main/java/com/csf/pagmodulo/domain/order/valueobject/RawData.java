package com.csf.pagmodulo.domain.order.valueobject;

import com.csf.pagmodulo.domain.shared.exceptions.InvalidFieldException;

public record RawData(
        Integer authorizationCode,
        String nsu,
        String reasonCode,
        String merchantAdviceCode
) {
    public RawData {
        validateAuthorizationCode(authorizationCode);
        validateNsu(nsu);
        validateReasonCode(reasonCode);
        validateMerchantAdviceCode(merchantAdviceCode);
    }

    private static void validateAuthorizationCode(Integer authorizationCode) {
        if (authorizationCode == null) throw new InvalidFieldException("RawData.authorizationCode", "authorizationCode não pode ser nulo");
        String codeStr = String.valueOf(authorizationCode);
        if (codeStr.length() != 6) throw new InvalidFieldException("RawData.authorizationCode", "authorizationCode deve ter exatamente 6 caracteres");
    }

    private static void validateNsu(String nsu) {
        if (nsu == null || nsu.isBlank()) throw new InvalidFieldException("RawData.nsu", "nsu não pode ser nulo ou em branco");
        if (nsu.length() < 4 || nsu.length() > 20) throw new InvalidFieldException("RawData.nsu", "nsu deve ter entre 4 e 20 caracteres");
    }

    private static void validateReasonCode(String reasonCode) {
        if (reasonCode == null || reasonCode.isBlank()) throw new InvalidFieldException("RawData.reasonCode", "reasonCode não pode ser nulo ou em branco");
        if (reasonCode.length() != 2) throw new InvalidFieldException("RawData.reasonCode", "reasonCode deve ter exatamente 2 caracteres");
    }

    private static void validateMerchantAdviceCode(String merchantAdviceCode) {
        if (merchantAdviceCode == null || merchantAdviceCode.isBlank()) throw new InvalidFieldException("RawData.merchantAdviceCode", "merchantAdviceCode não pode ser nulo ou em branco");
        if (merchantAdviceCode.length() != 2) throw new InvalidFieldException("RawData.merchantAdviceCode", "merchantAdviceCode deve ter exatamente 2 caracteres");
    }
}

