package com.csf.api_pag.presentation.handler.pagbank;

import com.csf.api_pag.infrastructure.pagbank.exeception.*;
import com.csf.api_pag.presentation.shared.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PagBankExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(PagBankExceptionHandler.class);

    @ExceptionHandler(PagBankAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthentication(PagBankAuthenticationException ex) {
        log.warn("Falha de autenticação no PagBank: {}", ex.getMessage());
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.UNAUTHORIZED.value(), "Não Autorizado", ex.getMessage())
                .retryable(ex.isRetryable())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    @ExceptionHandler(PagBankNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(PagBankNotFoundException ex) {
        log.warn("Recurso não encontrado no PagBank: {}", ex.getMessage());
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.NOT_FOUND.value(), "Não Encontrado", ex.getMessage())
                .retryable(ex.isRetryable())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(PagBankValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(PagBankValidationException ex) {
        log.warn("Erro de validação no PagBank — campo: '{}', erro: '{}', descrição: '{}'",
                ex.getField(), ex.getError(), ex.getDescription());
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.BAD_REQUEST.value(), "Requisição Inválida", ex.getMessage())
                .retryable(ex.isRetryable())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(PagBankRateLimitException.class)
    public ResponseEntity<ErrorResponse> handleRateLimit(PagBankRateLimitException ex) {
        log.warn("Limite de requisições do PagBank excedido: {}", ex.getMessage());
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.TOO_MANY_REQUESTS.value(), "Muitas Requisições", ex.getMessage())
                .retryable(ex.isRetryable())
                .retryAfterSeconds(ex.getRetryAfterSeconds())
                .build();
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(body);
    }

    @ExceptionHandler(PagBankTimeoutException.class)
    public ResponseEntity<ErrorResponse> handleTimeout(PagBankTimeoutException ex) {
        log.error("Tempo de requisição esgotado no PagBank: {}", ex.getMessage(), ex);
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.GATEWAY_TIMEOUT.value(), "Tempo de Gateway Esgotado", ex.getMessage())
                .retryable(ex.isRetryable())
                .build();
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(body);
    }

    @ExceptionHandler(PagBankServerException.class)
    public ResponseEntity<ErrorResponse> handleServer(PagBankServerException ex) {
        log.error("Erro no servidor do PagBank (status={}): {}", ex.getHttpStatus(), ex.getMessage());
        int status = ex.getHttpStatus() > 0 ? ex.getHttpStatus() : HttpStatus.BAD_GATEWAY.value();
        ErrorResponse body = ErrorResponse
                .builder(status, "Erro no Servidor do PagBank", ex.getMessage())
                .retryable(ex.isRetryable())
                .build();
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(PagBankUnknownException.class)
    public ResponseEntity<ErrorResponse> handleUnknown(PagBankUnknownException ex) {
        log.error("Erro inesperado no PagBank: {}", ex.getMessage(), ex);
        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro Interno do Servidor", ex.getMessage())
                .retryable(ex.isRetryable())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(PagBankException.class)
    public ResponseEntity<ErrorResponse> handleGenericPagBank(PagBankException ex) {
        log.error("Exceção não tratada do PagBank: {}", ex.getMessage(), ex);
        int status = ex.getHttpStatus() > 0 ? ex.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR.value();
        ErrorResponse body = ErrorResponse
                .builder(status, "Erro no PagBank", ex.getMessage())
                .retryable(ex.isRetryable())
                .build();
        return ResponseEntity.status(status).body(body);
    }
}

