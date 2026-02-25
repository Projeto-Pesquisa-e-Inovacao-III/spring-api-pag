package com.fillipe.pagmodulo.domain.checkout.exception;

public class CnpjIsNotSupportedException extends RuntimeException {
    public CnpjIsNotSupportedException() {
        super("CNPJ não é suportado nesta operação");
    }
}
