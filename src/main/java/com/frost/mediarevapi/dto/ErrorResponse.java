package com.frost.mediarevapi.dto;

public class ErrorResponse {
        private String message;
        private Object error;

        // Constructors
        public ErrorResponse() {}

        public ErrorResponse(String message) {
            this.message = message;
        }

        public ErrorResponse(String message, Object error) {
            this.message = message;
            this.error = error;
        }

        // Getters and Setters
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public Object getError() { return error; }
        public void setError(Object error) { this.error = error; }
    }
