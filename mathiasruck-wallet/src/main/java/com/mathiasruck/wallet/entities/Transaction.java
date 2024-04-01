package com.mathiasruck.wallet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TRANSACTION", uniqueConstraints = @UniqueConstraint(columnNames = {"TRANSACTION_ID"}))
public class Transaction extends BaseEntity {

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "ACCOUNT_ID_FROM")
    private String accountIdFrom;

    @Column(name = "ACCOUNT_ID_TO")
    private String accountIdTo;

    @Column(name = "AMOUNT")
    private Float amount;

}