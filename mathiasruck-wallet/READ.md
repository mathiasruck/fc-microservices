# Description

Agora que você entendeu os principais conceitos sobre microsserviços e da arquitetura baseada em eventos.
Desenvolva um microsserviço em sua linguagem de preferência que seja capaz de:

- [x] receber via Kafka os eventos gerados pelo microsserviço "Wallet Core"
- [x] persistir no banco de dados os balances atualizados para cada conta.
- [x] Crie um endpoint: "/balances/{account_id}" que exibe o balance atualizado.

## Requisitos para entrega

## Observações

- Nosso objetivo com esse desafio não é corrigir seu código ou verificar a qualidade da sua aplicação, mas sim
  garantir que você teve o entendendimento da importância da produção e consumo de eventos.
- Nosso suporte nesse desafio vai até o escopo conceitual sobre o entendimento dos eventos e não entrará no mérito
  da análise de seu código, e é exatamente por isso que estamos permitindo que você utilize a linguagem de programação
  que você ache mais conveniente.

# TODO

- [ ] Tudo deve rodar via Docker / Docker-compose
- [ ] Com um único docker-compose up -d todos os microsserviços, incluindo o da wallet core precisam estar disponíveis
  para que possamos fazer a correção.
- [ ] Não esqueça de rodar migrations e popular dados fictícios em ambos bancos de dados (wallet core e o microsserviço
  de balances) de forma automática quando os serviços subirem.
- [x] Gere o arquivo ".http" para realizarmos as chamadas em seu microsserviço da mesma forma que fizemos no
  microsserviço " wallet core"
- [x] Disponibilize o microsserviço na porta: 3003.

## Java

- [x] Criar registros via aplicação e exportar para sql
    - [x] Projeto Java/Spring
        - [x] banco
        - [x] dados
- [x] Adicionar liquibase ao projeto Java
- [x] Adicionar os scripts de insert e create table gereneciador de migrations
- [x] Adicionar scripts ao liquibase
- [x] Persistir mensagens recebidas do Kafka pelo projeto Java no banco
- [x] Retornar dados das transações recebidas e registradas no banco do projeto Java na URL
- [x] Criar arquivo .http com as chamadas para o projeto Java, utilizando os dados pré-inseridos
- [x] Add base entity with the basics for an entity to exist
- [X] Externalize username and password of DB

## Go

- [x] Adicionar gereneciador de migrations
- [x] Adicionar os scripts de insert e create table gereneciador de migrations
- [ ] Final validation: Apagar todo o docker e ver se tá tudo subindo e funcionando normal
    - [ ] Add delay ao início do Kafka?

# Improvements

- [ ] Logs: Use spring logs to log Kafka messages
    - [ ] Use advisor to log
- [ ] Return a better exception on HTTP methods
- [ ] Trocar data source Java para o Hikari
