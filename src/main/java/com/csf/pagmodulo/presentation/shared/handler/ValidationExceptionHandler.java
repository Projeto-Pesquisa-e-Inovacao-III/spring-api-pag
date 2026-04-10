package com.csf.pagmodulo.presentation.shared.handler;

import com.csf.pagmodulo.presentation.shared.exception.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> violations = extractBindingErrors(ex.getBindingResult().getFieldErrors(), ex.getBindingResult().getGlobalErrors().stream()
                .map(error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : error.getObjectName())
                .toList());

        return buildValidationResponse("Falha de validacao do body da requisicao", violations);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException ex) {
        List<String> violations = extractBindingErrors(ex.getBindingResult().getFieldErrors(), ex.getBindingResult().getGlobalErrors().stream()
                .map(error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : error.getObjectName())
                .toList());

        return buildValidationResponse("Falha de validacao dos parametros da requisicao", violations);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> violations = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toList();

        return buildValidationResponse("Falha de validacao por restricao", violations);
    }

    private List<String> extractBindingErrors(List<FieldError> fieldErrors, List<String> globalErrors) {
        List<String> violations = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            String message = fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "valor invalido";
            violations.add(fieldError.getField() + ": " + message);
        }

        violations.addAll(globalErrors);
        return violations;
    }

    private ResponseEntity<ErrorResponse> buildValidationResponse(String logMessage, List<String> violations) {
        String message = violations.isEmpty()
                ? "Dados da requisicao invalidos"
                : String.join("; ", violations);

        log.warn("{}: {}", logMessage, message);

        ErrorResponse body = ErrorResponse
                .builder(HttpStatus.BAD_REQUEST.value(), "Dados Invalidos", message)
                .retryable(false)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}

