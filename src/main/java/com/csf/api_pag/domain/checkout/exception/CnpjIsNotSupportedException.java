package com.csf.api_pag.domain.checkout.exception;

public class CnpjIsNotSupportedException extends RuntimeException {
    public CnpjIsNotSupportedException() {
        super("CNPJ não é suportado nesta operação");
    }
}
