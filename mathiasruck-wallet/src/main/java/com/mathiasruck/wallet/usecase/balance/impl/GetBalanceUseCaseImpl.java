package com.mathiasruck.wallet.usecase.balance.impl;

import com.mathiasruck.wallet.service.BalanceService;
import com.mathiasruck.wallet.service.dto.BalanceOutputDto;
import com.mathiasruck.wallet.usecase.balance.GetBalanceUseCase;
import com.mathiasruck.wallet.usecase.balance.dto.GetBalanceUseCaseOutputDto;
import org.springframework.stereotype.Component;

@Component
public class GetBalanceUseCaseImpl implements GetBalanceUseCase {

    private final BalanceService balanceService;

    public GetBalanceUseCaseImpl(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Override
    public GetBalanceUseCaseOutputDto getBalance(String accountId) throws Exception {
        BalanceOutputDto balance = balanceService.getBalance(accountId);
        return new GetBalanceUseCaseOutputDto(balance.accountId(), balance.balance());
    }
}
