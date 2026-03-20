package com.csf.pagmodulo.infrastructure.pagbank.exeception;

public class PagBankRateLimitException extends PagBankException {
    private final Integer retryAfterSeconds;

    public PagBankRateLimitException(String message, Integer retryAfterSeconds) {
        super(message, 429);
        this.retryAfterSeconds = retryAfterSeconds;
    }

    public Integer getRetryAfterSeconds() {
        return retryAfterSeconds;
    }

    @Override
    public boolean isRetryable() {
        return true;
    }
}
