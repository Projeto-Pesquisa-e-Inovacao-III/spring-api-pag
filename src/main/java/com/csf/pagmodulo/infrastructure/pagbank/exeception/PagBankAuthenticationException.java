package com.csf.pagmodulo.infrastructure.pagbank.exeception;

public class PagBankAuthenticationException extends PagBankException {
    public PagBankAuthenticationException(String message) {
        super(message, 401);
    }

    @Override
    public boolean isRetryable() {
        return false;
    }
}
