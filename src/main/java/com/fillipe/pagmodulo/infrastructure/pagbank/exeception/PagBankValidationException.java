package com.fillipe.pagmodulo.infrastructure.pagbank.exeception;

public class PagBankValidationException extends PagBankException {
    private final String field;
    private final String error;
    private final String description;

    public PagBankValidationException(String field, String error, String description) {
        super(String.format("Erro de validação no campo '%s': %s", field, description), 400);
        this.field = field;
        this.error = error;
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean isRetryable() {
        return false;
    }
}
