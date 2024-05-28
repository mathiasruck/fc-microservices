package com.mathiasruck.wallet.usecase.balance.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mathiasruck.wallet.service.BalanceService;
import com.mathiasruck.wallet.service.dto.BalanceInputDto;
import com.mathiasruck.wallet.usecase.balance.dto.UpdateBalanceUseCaseDto;

@ExtendWith(MockitoExtension.class)
public class UpdateBalanceUseCaseImplTest {

    @Mock
    private BalanceService balanceService;

    @InjectMocks
    private UpdateBalanceUseCaseImpl updateBalanceUseCase;

    @Test
    public void updateBalance() {
        UpdateBalanceUseCaseDto dto = new UpdateBalanceUseCaseDto("accFrom", 150.0F, "accTo", 50.0F);

        updateBalanceUseCase.updateBalance(dto);

        verify(balanceService).updateBalance(
                new BalanceInputDto(
                        dto.accountIdFrom(),
                        dto.balanceAccountFrom(),
                        dto.accountIdTo(),
                        dto.balanceAccountTo()
                ));
    }
}
