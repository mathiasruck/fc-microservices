package com.mathiasruck.wallet.service;

import com.mathiasruck.wallet.service.dto.BalanceInputDto;
import com.mathiasruck.wallet.service.dto.BalanceOutputDto;

public interface BalanceService {

    void updateBalance(BalanceInputDto dto);

    BalanceOutputDto getBalance(String accountId) throws Exception;
}