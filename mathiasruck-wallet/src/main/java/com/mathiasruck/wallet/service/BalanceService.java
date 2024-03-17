package com.mathiasruck.wallet.service;

import com.mathiasruck.wallet.service.dto.BalanceDto;
import com.mathiasruck.wallet.service.dto.BalanceOutputDto;

public interface BalanceService {

    void updateBalance(BalanceDto dto);

    BalanceOutputDto getBalance(String accountId);
}