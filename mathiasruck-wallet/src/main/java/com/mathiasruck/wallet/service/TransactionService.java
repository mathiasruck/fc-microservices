package com.mathiasruck.wallet.service;

import com.mathiasruck.wallet.service.dto.TransactionInputDto;

public interface TransactionService {

    void createTransaction(TransactionInputDto dto);
}
