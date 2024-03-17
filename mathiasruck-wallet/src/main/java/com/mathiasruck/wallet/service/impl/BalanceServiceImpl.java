package com.mathiasruck.wallet.service.impl;

import com.mathiasruck.wallet.entities.Balance;
import com.mathiasruck.wallet.repository.BalanceRepository;
import com.mathiasruck.wallet.service.BalanceService;
import com.mathiasruck.wallet.service.dto.BalanceDto;
import com.mathiasruck.wallet.service.dto.BalanceOutputDto;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public void updateBalance(BalanceDto dto) {
        Balance balanceTo = balanceRepository.getByAccountId(dto.accountIdTo());
        balanceTo.setBalance(dto.balanceAccountTo());
        balanceRepository.save(balanceTo);

        Balance balanceFrom = balanceRepository.getByAccountId(dto.accountIdFrom());
        balanceTo.setBalance(dto.balanceAccountFrom());
        balanceRepository.save(balanceFrom);
    }

    @Override
    public BalanceOutputDto getBalance(String accountId) {
        Balance balance = balanceRepository.getByAccountId( accountId);
        return new BalanceOutputDto(balance.getAccountId(), balance.getBalance());
    }
}
