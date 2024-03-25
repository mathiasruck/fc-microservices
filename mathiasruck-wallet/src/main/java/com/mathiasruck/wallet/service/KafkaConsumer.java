package com.mathiasruck.wallet.service;

import org.springframework.stereotype.Service;

@Service
public interface KafkaConsumer {

    void listenToTransactionsCreated(String message);
}
