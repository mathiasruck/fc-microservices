package com.mathiasruck.wallet.service.exceptions;

public class BalanceNotFoundException extends RuntimeException {

    public BalanceNotFoundException(String accountId) {
        super("Balance for accountId " + accountId + " not found.");
    }
}