package com.mathiasruck.wallet.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathiasruck.wallet.service.dto.BalanceUpdatedDto;
import com.mathiasruck.wallet.usecase.balance.UpdateBalanceUseCase;
import com.mathiasruck.wallet.usecase.balance.dto.UpdateBalanceUseCaseDto;
import com.mathiasruck.wallet.usecase.balance.factory.UpdateBalanceUseCaseDtoFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class BalanceKafkaConsumer {

    public static final String GROUP_ID_WALLET = "wallet";
    public static final String TOPIC_BALANCES = "balances";

    private final UpdateBalanceUseCase updateBalanceUseCase;
    private final UpdateBalanceUseCaseDtoFactory updateBalanceUseCaseDtoFactory;
    private final ObjectMapper objectMapper;

    public BalanceKafkaConsumer(UpdateBalanceUseCase updateBalanceUseCase, UpdateBalanceUseCaseDtoFactory updateBalanceUseCaseDtoFactory, ObjectMapper objectMapper) {
        this.updateBalanceUseCase = updateBalanceUseCase;
        this.updateBalanceUseCaseDtoFactory = updateBalanceUseCaseDtoFactory;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = TOPIC_BALANCES, groupId = GROUP_ID_WALLET)
    public void listenToBalanceUpdated(String message) throws JsonProcessingException {
        log.debug("Message received from kafka on topic '{}' with message '{}'.", TOPIC_BALANCES, message);

        BalanceUpdatedDto balanceUpdatedDto = objectMapper.readValue(message, BalanceUpdatedDto.class);
        if (balanceUpdatedDto != null && balanceUpdatedDto.payload() != null) {
            UpdateBalanceUseCaseDto updateBalanceUseCaseDto = updateBalanceUseCaseDtoFactory.from(balanceUpdatedDto);
            updateBalanceUseCase.updateBalance(updateBalanceUseCaseDto);
        } else {
            log.error("Error processing Kafka message on topic '{}' with message '{}'. Unable to parse JSON object.", TOPIC_BALANCES, message);
        }
    }
}
