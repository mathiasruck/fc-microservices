package com.mathiasruck.wallet.usecase.transaction.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mathiasruck.wallet.service.TransactionService;
import com.mathiasruck.wallet.service.dto.TransactionInputDto;
import com.mathiasruck.wallet.usecase.transaction.dto.CreateTransactionUseCaseDto;

@ExtendWith(MockitoExtension.class)
public class CreateCreateTransactionUseCaseImplTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private CreateCreateTransactionUseCaseImpl createTransactionUseCase;

    @Test
    public void createTransaction() {
        CreateTransactionUseCaseDto dto = new CreateTransactionUseCaseDto("1", "accFrom", "accTo", 100.0F);

        createTransactionUseCase.createTransaction(dto);

        verify(transactionService).createTransaction(
                new TransactionInputDto(
                        dto.id(),
                        dto.accountIdFrom(),
                        dto.accountIdTo(),
                        dto.amount()
                ));
    }
}
