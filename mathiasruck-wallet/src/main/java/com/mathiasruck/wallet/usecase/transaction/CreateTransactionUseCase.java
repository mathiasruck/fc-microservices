package com.mathiasruck.wallet.usecase.transaction;

import com.mathiasruck.wallet.usecase.transaction.dto.CreateTransactionUseCaseDto;

public interface CreateTransactionUseCase {

    void createTransaction(CreateTransactionUseCaseDto dto);
}
