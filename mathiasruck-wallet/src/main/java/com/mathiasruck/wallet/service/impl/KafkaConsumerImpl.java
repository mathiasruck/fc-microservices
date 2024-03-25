package com.mathiasruck.wallet.service.impl;

import com.mathiasruck.wallet.service.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumerImpl implements KafkaConsumer {

    public static final String TOPIC_TRANSACTIONS = "transactions";

    @KafkaListener(topics = TOPIC_TRANSACTIONS)
    public void listenToTransactionsCreated(String message) {
        System.out.printf("Message received from kafka: " + message);
    }
}
