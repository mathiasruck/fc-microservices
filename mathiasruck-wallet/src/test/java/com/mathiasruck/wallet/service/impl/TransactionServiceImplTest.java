package com.mathiasruck.wallet.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mathiasruck.wallet.entities.Transaction;
import com.mathiasruck.wallet.repository.TransactionRepository;
import com.mathiasruck.wallet.service.dto.TransactionInputDto;
import com.mathiasruck.wallet.service.factory.TransactionFactory;

public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionFactory transactionFactory;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTransaction() {
        TransactionInputDto dto = new TransactionInputDto("1", "accFrom", "accTo", 100.0F);
        Transaction transaction = new Transaction();
        transaction.setTransactionId("1");
        transaction.setAccountIdFrom("accFrom");
        transaction.setAccountIdTo("accTo");
        transaction.setAmount(100.0F);

        when(transactionFactory.from(dto)).thenReturn(transaction);

        transactionService.createTransaction(dto);

        verify(transactionFactory).from(dto);
        verify(transactionRepository).save(transaction);
    }
}
