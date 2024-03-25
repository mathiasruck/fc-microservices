package com.mathiasruck.wallet.usecase.balance;

import com.mathiasruck.wallet.usecase.balance.dto.UpdateBalanceUseCaseDto;

public interface UpdateBalanceUseCase {

    void updateBalance(UpdateBalanceUseCaseDto dto);
    
}
