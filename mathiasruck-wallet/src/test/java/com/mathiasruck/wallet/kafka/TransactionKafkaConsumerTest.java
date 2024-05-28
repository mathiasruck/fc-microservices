package com.mathiasruck.wallet.kafka;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathiasruck.wallet.service.dto.TransactionCreatedDto;
import com.mathiasruck.wallet.service.dto.TransactionCreatedPayloadDto;
import com.mathiasruck.wallet.usecase.transaction.CreateTransactionUseCase;
import com.mathiasruck.wallet.usecase.transaction.dto.CreateTransactionUseCaseDto;
import com.mathiasruck.wallet.usecase.transaction.factory.CreateTransactionUseCaseDtoFactory;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ExtendWith(MockitoExtension.class)
public class TransactionKafkaConsumerTest {

    @Mock
    private CreateTransactionUseCase createTransactionUseCase;

    @Mock
    private CreateTransactionUseCaseDtoFactory createTransactionUseCaseDtoFactory;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private TransactionKafkaConsumer transactionKafkaConsumer;

    @Test
    public void testListenToTransactionsCreated() throws JsonProcessingException {
        String message = "{\"payload\":{\"id\":\"1\",\"accountIdFrom\":\"accFrom\",\"accountIdTo\":\"accTo\",\"amount\":100.0}}";

        TransactionCreatedPayloadDto payload = new TransactionCreatedPayloadDto("1", "accFrom", "accTo", 100.0F);
        TransactionCreatedDto transactionCreatedDto = new TransactionCreatedDto("namne", payload);

        CreateTransactionUseCaseDto createTransactionUseCaseDto = new CreateTransactionUseCaseDto("1", "accFrom", "accTo", 100.0F);

        when(objectMapper.readValue(message, TransactionCreatedDto.class)).thenReturn(transactionCreatedDto);
        when(createTransactionUseCaseDtoFactory.from(transactionCreatedDto)).thenReturn(createTransactionUseCaseDto);

        transactionKafkaConsumer.listenToTransactionsCreated(message);

        verify(objectMapper).readValue(message, TransactionCreatedDto.class);
        verify(createTransactionUseCaseDtoFactory).from(transactionCreatedDto);
        verify(createTransactionUseCase).createTransaction(createTransactionUseCaseDto);
    }
}
