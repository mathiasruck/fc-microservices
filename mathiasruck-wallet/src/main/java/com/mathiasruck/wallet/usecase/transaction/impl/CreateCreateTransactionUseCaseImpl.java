package com.mathiasruck.wallet.usecase.transaction.impl;

import org.springframework.stereotype.Component;

import com.mathiasruck.wallet.service.TransactionService;
import com.mathiasruck.wallet.service.dto.TransactionInputDto;
import com.mathiasruck.wallet.usecase.transaction.CreateTransactionUseCase;
import com.mathiasruck.wallet.usecase.transaction.dto.CreateTransactionUseCaseDto;

@Component
public class CreateCreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final TransactionService transactionService;

    public CreateCreateTransactionUseCaseImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void createTransaction(CreateTransactionUseCaseDto dto) {
        transactionService.createTransaction(
                new TransactionInputDto(
                        dto.id(),
                        dto.accountIdFrom(),
                        dto.accountIdTo(),
                        dto.amount()
                ));
    }
}
