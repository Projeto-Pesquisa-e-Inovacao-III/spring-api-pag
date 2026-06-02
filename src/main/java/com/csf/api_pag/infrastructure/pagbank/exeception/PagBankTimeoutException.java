package com.csf.api_pag.infrastructure.pagbank.exeception;

public class PagBankTimeoutException extends PagBankException {
    public PagBankTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public boolean isRetryable() {
        return true;
    }
}
