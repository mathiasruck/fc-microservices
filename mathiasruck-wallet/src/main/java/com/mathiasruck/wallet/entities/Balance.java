package com.mathiasruck.wallet.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "BALANCE", uniqueConstraints = @UniqueConstraint(columnNames = {"ACCOUNT_ID"}))
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ACCOUNT_ID")
    private String accountId;

    @Column(name = "BALANCE")
    private Float balance;

}