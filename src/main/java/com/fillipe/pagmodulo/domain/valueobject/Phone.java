package com.fillipe.pagmodulo.domain.valueobject;

public record Phone(
        String country,
        String area,
        String number
) {
    public Phone {
        validateCountry(country);
        validateArea(area);
        validateNumber(number);
    }

    private static void validateCountry(String country) {
        if (country == null)
            throw new IllegalArgumentException("O código do país não pode ser nulo");
        if (country.length() < 2 || country.length() > 3)
            throw new IllegalArgumentException("O código do país deve ter 2-3 caracteres");
        if (!country.equals("+55"))
            throw new IllegalArgumentException("O código do país deve ser +55");
    }

    private static void validateArea(String area) {
        if (area == null || area.length() != 2 || !area.matches("\\d{2}"))
            throw new IllegalArgumentException("O código de area deve ter exatamente 2 dígitos");
    }

    private static void validateNumber(String number) {
        if (number == null || number.length() != 9)
            throw new IllegalArgumentException("O numero de celular deve ter exatamente 9 caracteres");
        if (!number.startsWith("9"))
            throw new IllegalArgumentException("O numero de celular deve começar com o digito 9");
        if (!number.matches("\\d{9}"))
            throw new IllegalArgumentException("O numero de celular deve apenas conter dígitos");
    }
}
