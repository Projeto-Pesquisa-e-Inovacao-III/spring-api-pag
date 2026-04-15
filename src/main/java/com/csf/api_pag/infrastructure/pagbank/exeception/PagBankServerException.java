package com.csf.api_pag.infrastructure.pagbank.exeception;

public class PagBankServerException extends PagBankException {
    public PagBankServerException(String message, int httpStatus) {
        super(message, httpStatus);
    }

    @Override
    public boolean isRetryable() {
        return true;
    }
}
