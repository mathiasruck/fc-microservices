create table clients (id varchar(255), name varchar(255), email varchar(255), created_at date);
create table accounts (id varchar(255), client_id varchar(255), balance float, created_at date);
create table transactions (id varchar(255), account_id_from varchar(255), account_id_to varchar(255), amount float, created_at date);


-- After accounts are created using api/client.http
UPDATE accounts SET balance = 100

