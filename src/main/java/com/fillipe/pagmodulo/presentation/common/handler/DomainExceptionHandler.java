package com.fillipe.pagmodulo.presentation.common.handler;

import com.fillipe.pagmodulo.domain.checkout.exception.CheckoutExpiredException;
import com.fillipe.pagmodulo.domain.shared.exceptions.InvalidFieldException;
import com.fillipe.pagmodulo.domain.checkout.exception.CheckoutInvalidStatusException;
import com.fillipe.pagmodulo.domain.checkout.exception.CheckoutNotFoundException;
import com.fillipe.pagmodulo.domain.checkout.exception.CnpjIsNotSupportedException;
import com.fillipe.pagmodulo.presentation.common.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DomainExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(DomainExceptionHandler.class);

    @ExceptionHandler(CheckoutNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCheckoutNotFound(CheckoutNotFoundException ex) {
        log.warn("Checkout não encontrado: {}", ex.getMessage());
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.NOT_FOUND.value(), "Não Encontrado", ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(CheckoutExpiredException.class)
    public ResponseEntity<ErrorResponse> handleCheckoutExpired(CheckoutExpiredException ex) {
        log.warn("Operação bloqueada — checkout expirado: {}", ex.getMessage());
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.CONFLICT.value(), "Checkout Expirado", ex.getMessage())
                .retryable(false)
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(CheckoutInvalidStatusException.class)
    public ResponseEntity<ErrorResponse> handleCheckoutInvalidStatus(CheckoutInvalidStatusException ex) {
        log.warn("Operação bloqueada — status inválido do checkout: {}", ex.getMessage());
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.CONFLICT.value(), "Status Inválido", ex.getMessage())
                .retryable(false)
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(CnpjIsNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleCnpjNotSupported(CnpjIsNotSupportedException ex) {
        log.warn("CNPJ não suportado: {}", ex.getMessage());
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.BAD_REQUEST.value(), "Operação Não Suportada", ex.getMessage())
                .retryable(false)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ErrorResponse> handleCheckoutInvalidField(InvalidFieldException ex) {
        log.warn("Campo inválido — campo: '{}', motivo: {}", ex.getField(), ex.getMessage());
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.BAD_REQUEST.value(), "Campo Inválido", ex.getMessage())
                .retryable(false)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
