package com.mathiasruck.wallet.usecase;

import com.mathiasruck.wallet.service.BalanceService;
import com.mathiasruck.wallet.service.dto.BalanceOutputDto;
import com.mathiasruck.wallet.usecase.dto.BalanceUseCaseOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanceUseCase {

    @Autowired
    private BalanceService balanceService;

    public BalanceUseCaseOutputDto getBalance(String accountId) {
        BalanceOutputDto balance = balanceService.getBalance(accountId);
    }
}
