package com.fillipe.pagmodulo.presentation.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final int status;
    private final String error;
    private final String message;
    private final Instant timestamp;
    private final Boolean retryable;
    private final Integer retryAfterSeconds;

    private ErrorResponse(Builder builder) {
        this.status = builder.status;
        this.error = builder.error;
        this.message = builder.message;
        this.timestamp = Instant.now();
        this.retryable = builder.retryable;
        this.retryAfterSeconds = builder.retryAfterSeconds;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Boolean getRetryable() {
        return retryable;
    }

    public Integer getRetryAfterSeconds() {
        return retryAfterSeconds;
    }

    public static Builder builder(int status, String error, String message) {
        return new Builder(status, error, message);
    }

    public static class Builder {
        private final int status;
        private final String error;
        private final String message;
        private Boolean retryable;
        private Integer retryAfterSeconds;

        private Builder(int status, String error, String message) {
            this.status = status;
            this.error = error;
            this.message = message;
        }

        public Builder retryable(boolean retryable) {
            this.retryable = retryable;
            return this;
        }

        public Builder retryAfterSeconds(Integer retryAfterSeconds) {
            this.retryAfterSeconds = retryAfterSeconds;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}

