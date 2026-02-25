package com.fillipe.pagmodulo.infrastructure.pagbank.exeception;

public class PagBankNotFoundException extends PagBankException {
    public PagBankNotFoundException(String resourceId) {
        super("Resource not found: " + resourceId, 404);
    }

    @Override
    public boolean isRetryable() {
        return false;
    }
}
