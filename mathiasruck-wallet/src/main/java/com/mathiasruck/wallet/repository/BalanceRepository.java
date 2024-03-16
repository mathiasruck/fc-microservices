package com.mathiasruck.wallet.repository;

import com.mathiasruck.wallet.entities.Balance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends CrudRepository<Balance, Long> {

    Balance getByAccountId(String accountId);

}
