package com.csf.pagmodulo.infrastructure.pagbank.exeception;

public class PagBankNotFoundException extends PagBankException {
    public PagBankNotFoundException(String resourceId) {
        super("Recurso não encontrado: " + resourceId, 404);
    }

    @Override
    public boolean isRetryable() {
        return false;
    }
}
