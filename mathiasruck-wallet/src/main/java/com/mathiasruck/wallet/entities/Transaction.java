package com.mathiasruck.wallet.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TRANSACTION", uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "TRANSACTION_ID"}))
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "ACCOUNT_ID_FROM")
    private String accountIdFrom;

    @Column(name = "ACCOUNT_ID_TO")
    private String accountIdTo;

    @Column(name = "AMOUNT")
    private Float amount;

}