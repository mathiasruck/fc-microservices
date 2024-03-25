package com.mathiasruck.wallet.service.impl;

import com.mathiasruck.wallet.entities.Transaction;
import com.mathiasruck.wallet.repository.TransactionRepository;
import com.mathiasruck.wallet.service.TransactionService;
import com.mathiasruck.wallet.service.dto.TransactionInputDto;
import com.mathiasruck.wallet.service.factory.TransactionFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionFactory transactionFactory;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionFactory transactionFactory) {
        this.transactionRepository = transactionRepository;
        this.transactionFactory = transactionFactory;
    }

    @Override
    public void createTransaction(TransactionInputDto dto) {
        Transaction transaction = transactionFactory.from(dto);
        transactionRepository.save(transaction);
    }


}
