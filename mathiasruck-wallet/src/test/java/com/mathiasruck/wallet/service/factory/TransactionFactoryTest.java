package com.mathiasruck.wallet.service.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.mathiasruck.wallet.entities.Transaction;
import com.mathiasruck.wallet.service.dto.TransactionInputDto;

class TransactionFactoryTest {

    @Test
    public void from() {
        TransactionFactory factory = new TransactionFactory();
        TransactionInputDto dto = new TransactionInputDto("1", "acc1", "acc2", 100.0F);

        Transaction transaction = factory.from(dto);

        assertEquals("1", transaction.getTransactionId());
        assertEquals("acc1", transaction.getAccountIdFrom());
        assertEquals("acc2", transaction.getAccountIdTo());
        assertEquals(100.0F, transaction.getAmount());
    }
}