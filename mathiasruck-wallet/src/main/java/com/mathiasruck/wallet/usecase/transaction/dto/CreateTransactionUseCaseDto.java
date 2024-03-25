package com.mathiasruck.wallet.usecase.transaction.dto;

public record CreateTransactionUseCaseDto(
        String id,
        String accountIdFrom,
        String accountIdTo,
        Float amount
) {
}
