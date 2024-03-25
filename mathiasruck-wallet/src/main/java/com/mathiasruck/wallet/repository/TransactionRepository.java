package com.mathiasruck.wallet.repository;

import com.mathiasruck.wallet.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
