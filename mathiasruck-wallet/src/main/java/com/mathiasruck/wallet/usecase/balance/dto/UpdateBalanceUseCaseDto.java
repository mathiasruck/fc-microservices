package com.mathiasruck.wallet.usecase.balance.dto;

public record UpdateBalanceUseCaseDto(
        String accountIdFrom,
        Float balanceAccountFrom,
        String accountIdTo,
        Float balanceAccountTo) {
}
