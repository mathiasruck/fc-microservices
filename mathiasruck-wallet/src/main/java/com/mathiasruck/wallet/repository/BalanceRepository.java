package com.mathiasruck.wallet.repository;

import com.mathiasruck.wallet.entities.Balance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends CrudRepository<Balance, Long> {

    Optional<Balance> getByAccountId(String accountId);

}
