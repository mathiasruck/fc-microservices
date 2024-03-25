package com.mathiasruck.wallet.service.dto;

public record TransactionInputDto(
        String id,
        String accountIdFrom,
        String accountIdTo,
        Float amount
) {
}
