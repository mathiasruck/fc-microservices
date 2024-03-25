package com.mathiasruck.wallet.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathiasruck.wallet.service.dto.TransactionCreatedDto;
import com.mathiasruck.wallet.usecase.transaction.CreateTransactionUseCase;
import com.mathiasruck.wallet.usecase.transaction.dto.CreateTransactionUseCaseDto;
import com.mathiasruck.wallet.usecase.transaction.factory.CreateTransactionUseCaseDtoFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class TransactionKafkaConsumer {

    public static final String TOPIC_TRANSACTIONS = "transactions";
    public static final String GROUP_ID_WALLET = "wallet";

    private final CreateTransactionUseCase createTransactionUseCase;
    private final CreateTransactionUseCaseDtoFactory createTransactionUseCaseDtoFactory;
    private final ObjectMapper objectMapper;

    public TransactionKafkaConsumer(CreateTransactionUseCase createTransactionUseCase, CreateTransactionUseCaseDtoFactory createTransactionUseCaseDtoFactory, ObjectMapper objectMapper) {
        this.createTransactionUseCase = createTransactionUseCase;
        this.createTransactionUseCaseDtoFactory = createTransactionUseCaseDtoFactory;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = TOPIC_TRANSACTIONS, groupId = GROUP_ID_WALLET)
    public void listenToTransactionsCreated(String message) throws JsonProcessingException {
        log.debug("Message received from kafka on topic '{}' with message '{}'.", TOPIC_TRANSACTIONS, message);

        TransactionCreatedDto transactionCreatedDto = objectMapper.readValue(message, TransactionCreatedDto.class);
        if (transactionCreatedDto != null && transactionCreatedDto.payload() != null) {
            CreateTransactionUseCaseDto createTransactionUseCaseDto = createTransactionUseCaseDtoFactory.from(transactionCreatedDto);
            createTransactionUseCase.createTransaction(createTransactionUseCaseDto);
        } else {
            log.error("Error processing Kafka message on topic '{}' with message '{}'. Unable to parse JSON object.", TOPIC_TRANSACTIONS, message);
        }
    }
}
