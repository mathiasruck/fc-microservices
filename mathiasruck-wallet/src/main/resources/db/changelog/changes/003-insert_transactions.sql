-- liquibase formatted sql

-- changeset liquibase:1
INSERT INTO transaction (account_id_from, account_id_to, amount, transaction_id)
VALUES ('3f19de80-a22c-48a6-8c76-69a7cb792aff', '2311e1f8-0af7-4d7d-9c59-7db23af80d96', 5,
        '570b44f2-84bb-4b31-8744-f90ad313f2bf');

-- changeset liquibase:2
INSERT INTO transaction (account_id_from, account_id_to, amount, transaction_id)
VALUES ('3f19de80-a22c-48a6-8c76-69a7cb792aff', '2311e1f8-0af7-4d7d-9c59-7db23af80d96', 5,
        '9ea11c96-6625-4010-add6-c907331fe448');

-- changeset liquibase:3
INSERT INTO transaction (account_id_from, account_id_to, amount, transaction_id)
VALUES ('3f19de80-a22c-48a6-8c76-69a7cb792aff', '2311e1f8-0af7-4d7d-9c59-7db23af80d96', 5,
        '4daf342c-9ab3-45dc-bf4d-49bd776bda42');
