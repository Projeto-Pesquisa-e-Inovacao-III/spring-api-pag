package com.fillipe.pagmodulo.domain.shared.valueobjects;

import com.fillipe.pagmodulo.domain.shared.exceptions.InvalidFieldException;

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
        //Todo: permitir 55 e +55
        if (country == null)
            throw new InvalidFieldException("phone.country", "não pode ser nulo");
        if (country.length() < 2 || country.length() > 3)
            throw new InvalidFieldException("phone.country", "deve ter 2-3 caracteres");
        if (!country.equals("+55"))
            throw new InvalidFieldException("phone.country", "deve ser +55");
    }

    private static void validateArea(String area) {
        if (area == null || area.length() != 2 || !area.matches("\\d{2}"))
            throw new InvalidFieldException("phone.area", "deve ter exatamente 2 dígitos");
    }

    private static void validateNumber(String number) {
        if (number == null || number.length() != 9)
            throw new InvalidFieldException("phone.number", "deve ter exatamente 9 caracteres");
        if (!number.startsWith("9"))
            throw new InvalidFieldException("phone.number", "deve começar com o dígito 9");
        if (!number.matches("\\d{9}"))
            throw new InvalidFieldException("phone.number", "deve conter apenas dígitos");
    }
}
