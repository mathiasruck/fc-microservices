package com.mathiasruck.wallet.kafka;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathiasruck.wallet.service.dto.BalanceUpdatedDto;
import com.mathiasruck.wallet.usecase.balance.UpdateBalanceUseCase;
import com.mathiasruck.wallet.usecase.balance.dto.UpdateBalanceUseCaseDto;
import com.mathiasruck.wallet.usecase.balance.factory.UpdateBalanceUseCaseDtoFactory;

@ExtendWith(MockitoExtension.class)
class BalanceKafkaConsumerTest {

    @Mock
    private UpdateBalanceUseCase updateBalanceUseCase;
    @Mock
    private UpdateBalanceUseCaseDtoFactory updateBalanceUseCaseDtoFactory;
    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private BalanceKafkaConsumer balanceKafkaConsumer;

    @Captor
    private ArgumentCaptor<UpdateBalanceUseCaseDto> updateBalanceUseCaseDtoCaptor;

    @Test
    public void listenToBalanceUpdated_shouldCorrectlyUpdateTheBalance() throws JsonProcessingException {
        String message = "{"
                + "  \"name\": \"Balance Update\","
                + "  \"payload\": {"
                + "    \"accountIdFrom\": \"account123\","
                + "    \"balanceAccountIdFrom\": 1000.5,"
                + "    \"accountIdTo\": \"account456\","
                + "    \"balanceAccountIdTo\": 2000.75"
                + "  }"
                + "}";
        when(objectMapper.readValue(message, BalanceUpdatedDto.class)).thenCallRealMethod();
        when(updateBalanceUseCaseDtoFactory.from(any(BalanceUpdatedDto.class))).thenCallRealMethod();

        balanceKafkaConsumer.listenToBalanceUpdated(message);

        verify(updateBalanceUseCaseDtoFactory).from(any(BalanceUpdatedDto.class));
        verify(updateBalanceUseCase).updateBalance(updateBalanceUseCaseDtoCaptor.capture());
        UpdateBalanceUseCaseDto dto = updateBalanceUseCaseDtoCaptor.getValue();
        assertAll("BalanceUpdatedPayloadDto fields validation",
                () -> assertEquals(dto.accountIdFrom(), "account123"),
                () -> assertEquals(dto.balanceAccountFrom(), 1000.5F),
                () -> assertEquals(dto.accountIdTo(), "account456"),
                () -> assertEquals(dto.balanceAccountTo(), 2000.75F)
        );
    }
}