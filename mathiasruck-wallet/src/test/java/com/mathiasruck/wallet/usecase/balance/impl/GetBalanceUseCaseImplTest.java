package com.mathiasruck.wallet.usecase.balance.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mathiasruck.wallet.service.BalanceService;
import com.mathiasruck.wallet.service.dto.BalanceOutputDto;
import com.mathiasruck.wallet.usecase.balance.dto.GetBalanceUseCaseOutputDto;

@ExtendWith(MockitoExtension.class)
public class GetBalanceUseCaseImplTest {

    @Mock
    private BalanceService balanceService;

    @InjectMocks
    private GetBalanceUseCaseImpl getBalanceUseCase;

    @Test
    public void testGetBalance() throws Exception {
        String accountId = "acc123";
        BalanceOutputDto balanceOutputDto = new BalanceOutputDto(accountId, 200.0F);

        when(balanceService.getBalance(accountId)).thenReturn(balanceOutputDto);

        GetBalanceUseCaseOutputDto result = getBalanceUseCase.getBalance(accountId);

        assertEquals(accountId, result.accountId());
        assertEquals(200.0F, result.balance());
        verify(balanceService).getBalance(accountId);
    }
}
