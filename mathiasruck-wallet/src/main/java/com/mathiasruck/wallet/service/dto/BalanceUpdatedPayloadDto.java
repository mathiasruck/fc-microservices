package com.mathiasruck.wallet.service.dto;

public record BalanceUpdatedPayloadDto(
        String accountIdFrom,
        Float balanceAccountIdFrom,
        String accountIdTo,
        Float balanceAccountIdTo) {
}
