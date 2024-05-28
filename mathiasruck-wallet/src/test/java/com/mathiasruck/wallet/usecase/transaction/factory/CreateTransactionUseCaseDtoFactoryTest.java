package com.mathiasruck.wallet.usecase.transaction.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mathiasruck.wallet.service.dto.TransactionCreatedDto;
import com.mathiasruck.wallet.service.dto.TransactionCreatedPayloadDto;
import com.mathiasruck.wallet.usecase.transaction.dto.CreateTransactionUseCaseDto;

public class CreateTransactionUseCaseDtoFactoryTest {

    private CreateTransactionUseCaseDtoFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new CreateTransactionUseCaseDtoFactory();
    }

    @Test
    public void from() {
        TransactionCreatedPayloadDto payload = new TransactionCreatedPayloadDto("1", "accFrom", "accTo", 100.0F);
        TransactionCreatedDto message = new TransactionCreatedDto("name", payload);

        CreateTransactionUseCaseDto result = factory.from(message);

        assertEquals("1", result.id());
        assertEquals("accFrom", result.accountIdFrom());
        assertEquals("accTo", result.accountIdTo());
        assertEquals(100.0F, result.amount());
    }
}
