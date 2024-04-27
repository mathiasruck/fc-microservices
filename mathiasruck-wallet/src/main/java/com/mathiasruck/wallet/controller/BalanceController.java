package com.mathiasruck.wallet.controller;

import com.mathiasruck.wallet.controller.dto.BalanceWebOutputDto;
import com.mathiasruck.wallet.usecase.balance.dto.GetBalanceUseCaseOutputDto;
import com.mathiasruck.wallet.usecase.balance.impl.GetBalanceUseCaseImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/balances")
public class BalanceController {

    private final GetBalanceUseCaseImpl useCase;

    public BalanceController(GetBalanceUseCaseImpl useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{account_id}")
    public @ResponseBody BalanceWebOutputDto getBalance(@PathVariable(name = "account_id") String accountId) throws Exception {
        GetBalanceUseCaseOutputDto balance = useCase.getBalance(accountId);
        return new BalanceWebOutputDto(balance.accountId(), balance.balance());
    }
}
