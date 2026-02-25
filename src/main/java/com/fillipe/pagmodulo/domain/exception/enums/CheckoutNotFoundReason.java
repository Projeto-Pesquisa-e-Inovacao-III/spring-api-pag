package com.fillipe.pagmodulo.domain.exception.enums;

public enum CheckoutNotFoundReason {
    NOT_EXISTS("Não existe");

    private final String message;

    CheckoutNotFoundReason(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

