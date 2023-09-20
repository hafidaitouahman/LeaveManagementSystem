package com.leave.backend.Exceptions;


public class InsufficientLeaveQuotaException extends RuntimeException {
    public InsufficientLeaveQuotaException(String message) {
        super(message);
    }
}
