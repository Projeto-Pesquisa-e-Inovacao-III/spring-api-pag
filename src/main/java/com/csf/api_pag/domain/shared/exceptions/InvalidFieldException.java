package com.csf.api_pag.domain.shared.exceptions;

public class InvalidFieldException extends RuntimeException {

    private final String field;

    public InvalidFieldException(String field, String reason) {
        super(String.format("Campo '%s' inválido: %s", field, reason));
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
