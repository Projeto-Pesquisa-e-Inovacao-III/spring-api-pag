package com.fillipe.pagmodulo.infrastructure.pagbank.exeception;

public abstract class PagBankException extends RuntimeException {
    private final int httpStatus;

    protected PagBankException(String message) {
        this(message, 0);
    }

    protected PagBankException(String message, int httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    protected PagBankException(String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = 0;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public abstract boolean isRetryable();
}
