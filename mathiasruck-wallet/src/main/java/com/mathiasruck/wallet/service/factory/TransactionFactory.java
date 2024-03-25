package com.mathiasruck.wallet.service.factory;

import com.mathiasruck.wallet.entities.Transaction;
import com.mathiasruck.wallet.service.dto.TransactionInputDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionFactory {

    public Transaction from(TransactionInputDto dto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(dto.id());
        transaction.setAccountIdFrom(dto.accountIdFrom());
        transaction.setAccountIdTo(dto.accountIdTo());
        transaction.setAmount(dto.amount());
        return transaction;
    }
}
