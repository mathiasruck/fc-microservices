package com.mathiasruck.wallet.usecase.balance.factory;

import com.mathiasruck.wallet.service.dto.BalanceUpdatedDto;
import com.mathiasruck.wallet.usecase.balance.dto.UpdateBalanceUseCaseDto;
import org.springframework.stereotype.Component;

@Component
public class UpdateBalanceUseCaseDtoFactory {

    public UpdateBalanceUseCaseDto from(BalanceUpdatedDto balanceUpdatedDto) {

        return new UpdateBalanceUseCaseDto(
                balanceUpdatedDto.payload().accountIdFrom(),
                balanceUpdatedDto.payload().balanceAccountIdFrom(),
                balanceUpdatedDto.payload().accountIdTo(),
                balanceUpdatedDto.payload().balanceAccountIdTo()
        );
    }
}
