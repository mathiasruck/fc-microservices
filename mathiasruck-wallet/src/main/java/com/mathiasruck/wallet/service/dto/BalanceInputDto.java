package com.mathiasruck.wallet.service.dto;

public record BalanceInputDto(
        String accountIdFrom,
        Float balanceAccountFrom,
        String accountIdTo,
        Float balanceAccountTo) {
}
