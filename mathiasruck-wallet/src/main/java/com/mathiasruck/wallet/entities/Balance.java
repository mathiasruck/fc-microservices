package com.mathiasruck.wallet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BALANCE", uniqueConstraints = @UniqueConstraint(columnNames = {"ACCOUNT_ID"}))
public class Balance extends BaseEntity {

    @Column(name = "ACCOUNT_ID")
    private String accountId;

    @Column(name = "BALANCE")
    private Float balance;

}