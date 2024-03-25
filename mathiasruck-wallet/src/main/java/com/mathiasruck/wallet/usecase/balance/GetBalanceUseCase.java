package com.mathiasruck.wallet.usecase.balance;

import com.mathiasruck.wallet.usecase.balance.dto.GetBalanceUseCaseOutputDto;

public interface GetBalanceUseCase {

    GetBalanceUseCaseOutputDto getBalance(String accountId) throws Exception;
}
