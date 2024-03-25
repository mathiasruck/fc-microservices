package com.mathiasruck.wallet.service.impl;

import com.mathiasruck.wallet.entities.Balance;
import com.mathiasruck.wallet.repository.BalanceRepository;
import com.mathiasruck.wallet.service.BalanceService;
import com.mathiasruck.wallet.service.dto.BalanceInputDto;
import com.mathiasruck.wallet.service.dto.BalanceOutputDto;
import com.mathiasruck.wallet.service.exceptions.BalanceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public void updateBalance(BalanceInputDto dto) {
        Balance balanceTo = balanceRepository.getByAccountId(dto.accountIdTo())
                .orElseGet(createBalance(dto.accountIdTo()));
        balanceTo.setBalance(dto.balanceAccountTo());
        balanceRepository.save(balanceTo);

        Balance balanceFrom = balanceRepository.getByAccountId(dto.accountIdFrom())
                .orElseGet(createBalance(dto.accountIdFrom()));
        balanceFrom.setBalance(dto.balanceAccountFrom());
        balanceRepository.save(balanceFrom);
    }

    @Override
    public BalanceOutputDto getBalance(String accountId) throws Exception {
        Balance balance = balanceRepository.getByAccountId(accountId)
                .orElseThrow(() -> new BalanceNotFoundException(accountId));
        return new BalanceOutputDto(balance.getAccountId(), balance.getBalance());
    }

    private Supplier<Balance> createBalance(String accountId) {
        return () -> {
            Balance balance = new Balance();
            balance.setAccountId(accountId);
            return balance;
        };
    }
}
