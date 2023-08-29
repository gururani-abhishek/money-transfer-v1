package com.hsbc.exception;

public class NotAdequateMoney extends RuntimeException {
    public NotAdequateMoney(String message) {
        super(message);
    }
}
