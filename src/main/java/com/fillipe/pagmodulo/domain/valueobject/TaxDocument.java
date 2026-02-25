package com.fillipe.pagmodulo.domain.valueobject;

import com.fillipe.pagmodulo.domain.exception.CnpjIsNotSupportedException;

public class TaxDocument {

    private final String value;
    private final TaxDocumentType type;

    public TaxDocument(String value, TaxDocumentType type) {
        validateValue(value, type);
        this.type = type;
        this.value = cleanValue(value, type);
    }

    public String getValue() {
        return value;
    }

    public TaxDocumentType getType() {
        return type;
    }

    private static String cleanValue(String value, TaxDocumentType type) {
        if (type.equals(TaxDocumentType.CNPJ)) {
            throw new CnpjIsNotSupportedException();
        }
        if (type.equals(TaxDocumentType.CPF)) {
            return clearCpf(value);
        }
        return null;
    }

    private static void validateValue(String value, TaxDocumentType type) {
        if (type.equals(TaxDocumentType.CPF)) {
            if (!isCpfValid(value)) {
                throw new IllegalArgumentException("CPF inválido: " + value);
            }
        }
        if (type.equals(TaxDocumentType.CNPJ)) {
            throw new CnpjIsNotSupportedException();
        }
    }

    private static boolean isCpfValid(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            return false;
        }

        String cleaned = clearCpf(cpf);

        if (cleaned.length() != 11) {
            return false;
        }

        boolean allDigitsRepeated = cleaned.matches("(\\d)\\1{10}");

        if (allDigitsRepeated) {
            return false;
        }

        // Em ASCII se você remove o char '0' por outro char numérico você consegue o código para ele em número
        // Por exemplo, o numéro '1' tem a posição 49 e o '0' 48, logo '1' - '0' = 49 - 48 = 1
        int[] digits = cleaned.chars().map(c -> c - '0').toArray();

        int firstDigit = calculateVerificationDigit(digits, 10);
        if (digits[9] != firstDigit) {
            return false;
        }

        int secondDigit = calculateVerificationDigit(digits, 11);
        return digits[10] == secondDigit;
    }

    private static int calculateVerificationDigit(int[] digits, int startWeight) {
        int sum = 0;
        int weight = startWeight;

        for (int i = 0; i < startWeight - 1; i++) {
            sum += digits[i] * weight;
            weight--;
        }

        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private static String clearCpf(String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }
}
