package com.restassignment.rest_apis.exception;

public class InvalidPayload extends RuntimeException {
    public InvalidPayload(String message) {
        super(message);
    }
}