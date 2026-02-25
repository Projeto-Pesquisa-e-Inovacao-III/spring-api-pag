package com.fillipe.pagmodulo.infrastructure.pagbank.exeception;

public class PagBankUnknownException extends PagBankException {
    public PagBankUnknownException(String message) {
        super(message);
    }

    public PagBankUnknownException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public boolean isRetryable() {
        return false;
    }
}
