package com.mathiasruck.wallet.service.dto;

public record TransactionCreatedPayloadDto(String id, String accountIdFrom, String accountIdTo, Float amount) {
}
