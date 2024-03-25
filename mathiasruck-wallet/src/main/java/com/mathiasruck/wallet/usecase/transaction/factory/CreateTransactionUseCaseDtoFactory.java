package com.mathiasruck.wallet.usecase.transaction.factory;

import com.mathiasruck.wallet.service.dto.TransactionCreatedDto;
import com.mathiasruck.wallet.usecase.transaction.dto.CreateTransactionUseCaseDto;
import org.springframework.stereotype.Component;

@Component
public class CreateTransactionUseCaseDtoFactory {


    public CreateTransactionUseCaseDto from(TransactionCreatedDto transactionCreatedDto) {
        return new CreateTransactionUseCaseDto(
                transactionCreatedDto.payload().id(),
                transactionCreatedDto.payload().accountIdFrom(),
                transactionCreatedDto.payload().accountIdTo(),
                transactionCreatedDto.payload().amount()
        );
    }
}
