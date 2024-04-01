-- liquibase formatted sql

-- changeset liquibase:1
create table balance
(
    id         bigint auto_increment primary key,
    account_id varchar(255) null,
    balance    float        null,
    constraint balance_account_unique unique (account_id)
);

-- changeset liquibase:2
create table transaction
(
    id              bigint auto_increment primary key,
    account_id_from varchar(255) null,
    account_id_to   varchar(255) null,
    amount          float        null,
    transaction_id  varchar(255) null,
    constraint transaction_id_unique unique (transaction_id)
);

