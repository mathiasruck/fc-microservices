package com.mathiasruck.wallet.service.dto;

public record BalanceDto (
        String accountIdFrom,
        Float balanceAccountFrom,
        String accountIdTo,
        Float balanceAccountTo){}
