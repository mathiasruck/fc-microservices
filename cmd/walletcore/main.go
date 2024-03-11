package main

import (
	"context"
	"database/sql"
	"fmt"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/database"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/event"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/usecase/create_account"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/usecase/create_client"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/usecase/create_transaction"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/web"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/web/webserver"
	"github.com.br/mathiasruck/fc-ms-wallet/pkg/events"
	"github.com.br/mathiasruck/fc-ms-wallet/pkg/uow"
	_ "github.com/go-sql-driver/mysql"
)

func main() {
	db, err := sql.Open("mysql", fmt.Sprintf("%s:%s@tcp(%s:%s)/%s?charset=utf8&parseTime=True&loc=Local", "root", "root", "localhost", "3306", "wallet"))
	if err != nil {
		panic(err)
	}

	defer db.Close()

	eventDispatcher := events.NewEventDispatcher()
	transactionCreatedEvent := event.NewTransactionCreated()
	//eventDispatcher.Register("TransactionCreated", handler)

	clentDb := database.NewClientDB(db)
	accountDb := database.NewAccountDB(db)

	ctx := context.Background()
	uow := uow.NewUow(ctx, db)

	uow.Register("AccountDB", func(tx *sql.Tx) interface{} {
		return database.NewAccountDB(db)
	})

	uow.Register("TransactionDB", func(tx *sql.Tx) interface{} {
		return database.NewTransactionDB(db)
	})
	createTransactionUseCase := create_transaction.NewCreateTransactionUseCase(uow, eventDispatcher, transactionCreatedEvent)

	createClientUseCase := create_client.NewCreateClientUseCase(clentDb)
	createAccountUseCase := create_account.NewCreateAccountUseCase(accountDb, clentDb)

	webserver := webserver.NewWebServer(":3000")

	clientHandler := web.NewWebClientHandler(*createClientUseCase)
	accountHandler := web.NewWebAccountHandler(*createAccountUseCase)
	transactionHandler := web.NewWebTransactionHandler(*createTransactionUseCase)

	webserver.AddHandler("/clients", clientHandler.CreateClient)
	webserver.AddHandler("/accounts", accountHandler.CreateAccount)
	webserver.AddHandler("/transactions", transactionHandler.CreateTransaction)

	webserver.Start()
}
