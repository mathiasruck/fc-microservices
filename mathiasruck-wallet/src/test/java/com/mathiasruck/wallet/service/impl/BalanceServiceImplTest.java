package com.mathiasruck.wallet.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mathiasruck.wallet.entities.Balance;
import com.mathiasruck.wallet.repository.BalanceRepository;
import com.mathiasruck.wallet.service.dto.BalanceInputDto;
import com.mathiasruck.wallet.service.dto.BalanceOutputDto;
import com.mathiasruck.wallet.service.exceptions.BalanceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceImplTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    @Test
    public void getBalance_Found() throws Exception {
        Balance balance = new Balance();
        balance.setAccountId("123");
        balance.setBalance(100.0F);

        when(balanceRepository.getByAccountId("123")).thenReturn(Optional.of(balance));

        BalanceOutputDto result = balanceService.getBalance("123");

        assertEquals("123", result.accountId());
        assertEquals(100.0F, result.balance());
    }

    @Test
    public void getBalance_NotFound() {
        when(balanceRepository.getByAccountId("123")).thenReturn(Optional.empty());

        assertThrows(BalanceNotFoundException.class, () -> {
            balanceService.getBalance("123");
        });
    }

    @Test
    public void updateBalance() {
        Balance balanceTo = new Balance();
        balanceTo.setAccountId("toAccount");
        balanceTo.setBalance(50.0F);

        Balance balanceFrom = new Balance();
        balanceFrom.setAccountId("fromAccount");
        balanceFrom.setBalance(150.0F);

        BalanceInputDto dto = new BalanceInputDto("fromAccount", 150.0F, "toAccount", 50.0F);

        when(balanceRepository.getByAccountId("toAccount")).thenReturn(Optional.of(balanceTo));
        when(balanceRepository.getByAccountId("fromAccount")).thenReturn(Optional.of(balanceFrom));

        balanceService.updateBalance(dto);

        verify(balanceRepository).save(balanceTo);
        verify(balanceRepository).save(balanceFrom);

        assertEquals(50.0F, balanceTo.getBalance());
        assertEquals(150.0F, balanceFrom.getBalance());
    }

    @Test
    public void updateBalance_addBalance() {
        BalanceInputDto dto = new BalanceInputDto("fromAccount", 150.0F, "toAccount", 50.0F);

        when(balanceRepository.getByAccountId("toAccount")).thenReturn(Optional.empty());
        when(balanceRepository.getByAccountId("fromAccount")).thenReturn(Optional.empty());

        balanceService.updateBalance(dto);

        verify(balanceRepository, times(2)).save(any(Balance.class));
    }
}