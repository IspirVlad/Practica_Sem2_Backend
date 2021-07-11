package com.marketplace.exception;

public class CustomConflictException extends Exception {
    private String conflict;

    public CustomConflictException(com.marketplace.exception.Conflict conflict) {
        super(conflict.toString());
        this.conflict = conflict.toString();
    }

    public String getErrorMessage() {
        return conflict;
    }
}
