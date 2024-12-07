package com.jaeshim.order.management.api.ordermanagement.domain.exception;

public class LackOfAmountException extends RuntimeException {
    public LackOfAmountException(String message) {
        super(message);
    }
}
