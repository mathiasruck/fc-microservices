package com.mathiasruck.wallet.controller;

import com.mathiasruck.wallet.usecase.BalanceUseCase;
import com.mathiasruck.wallet.usecase.dto.BalanceUseCaseOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/balances")
public class BalanceController {

    @Autowired
    private BalanceUseCase useCase;

    @GetMapping("/{account_id}")
    public @ResponseBody BalanceUseCaseOutputDto getBalance(@PathVariable String account_id) {
        BalanceUseCaseOutputDto balance = useCase.getBalance(account_id);
        return new
    }
}
