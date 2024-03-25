package com.mathiasruck.wallet.usecase.balance.impl;

import com.mathiasruck.wallet.service.BalanceService;
import com.mathiasruck.wallet.service.dto.BalanceInputDto;
import com.mathiasruck.wallet.usecase.balance.UpdateBalanceUseCase;
import com.mathiasruck.wallet.usecase.balance.dto.UpdateBalanceUseCaseDto;
import org.springframework.stereotype.Service;

@Service
public class UpdateBalanceUseCaseImpl implements UpdateBalanceUseCase {

    private final BalanceService balanceService;

    public UpdateBalanceUseCaseImpl(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Override
    public void updateBalance(UpdateBalanceUseCaseDto dto) {
        balanceService.updateBalance(
                new BalanceInputDto(
                        dto.accountIdFrom(),
                        dto.balanceAccountFrom(),
                        dto.accountIdTo(),
                        dto.balanceAccountTo())
        );
    }
}
