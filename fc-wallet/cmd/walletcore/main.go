package main

import (
	"context"
	"database/sql"
	"errors"
	"fmt"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/database"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/event"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/event/handler"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/usecase/create_account"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/usecase/create_client"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/usecase/create_transaction"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/web"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/web/webserver"
	"github.com.br/mathiasruck/fc-ms-wallet/pkg/events"
	"github.com.br/mathiasruck/fc-ms-wallet/pkg/kafka"
	"github.com.br/mathiasruck/fc-ms-wallet/pkg/uow"
	ckafka "github.com/confluentinc/confluent-kafka-go/kafka"
	_ "github.com/go-sql-driver/mysql"
	"github.com/golang-migrate/migrate/v4"
	"github.com/golang-migrate/migrate/v4/database/mysql"
	_ "github.com/golang-migrate/migrate/v4/source/file"
	"log"
)

func main() {
	db, err := sql.Open("mysql", fmt.Sprintf("%s:%s@tcp(%s:%s)/%s?charset=utf8&parseTime=True&loc=Local&multiStatements=true", "root", "root", "mysql", "3306", "wallet"))
	if err != nil {
		panic(err)
	}

	err = executeMigrations(db)

	defer func(db *sql.DB) {
		err := db.Close()
		if err != nil {

		}
	}(db)

	if err != nil {
		panic(err)
	}

	configMap := ckafka.ConfigMap{
		"bootstrap.servers": "kafka:29092",
		"group.id":          "wallet",
	}

	kafkaProducer := kafka.NewKafkaProducer(&configMap)

	eventDispatcher := events.NewEventDispatcher()
	eventDispatcher.Register("TransactionCreated", handler.NewTransactionCreatedKafkaHandler(kafkaProducer))
	eventDispatcher.Register("BalanceUpdated", handler.NewUpdateBalanceKafkaHandler(kafkaProducer))
	transactionCreatedEvent := event.NewTransactionCreated()
	balanceUpdatedEvent := event.NewBalanceUpdated()

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
	createTransactionUseCase := create_transaction.NewCreateTransactionUseCase(uow, eventDispatcher, transactionCreatedEvent, balanceUpdatedEvent)

	createClientUseCase := create_client.NewCreateClientUseCase(clentDb)
	createAccountUseCase := create_account.NewCreateAccountUseCase(accountDb, clentDb)

	webServer := webserver.NewWebServer(":8080")

	clientHandler := web.NewWebClientHandler(*createClientUseCase)
	accountHandler := web.NewWebAccountHandler(*createAccountUseCase)
	transactionHandler := web.NewWebTransactionHandler(*createTransactionUseCase)

	webServer.AddHandler("/clients", clientHandler.CreateClient)
	webServer.AddHandler("/accounts", accountHandler.CreateAccount)
	webServer.AddHandler("/transactions", transactionHandler.CreateTransaction)

	fmt.Println("Server is running")
	webServer.Start()
}

func executeMigrations(db *sql.DB) error {
	driver, err := mysql.WithInstance(db, &mysql.Config{})
	if err != nil {
		log.Fatal("Error creating migration driver: ", err)
	}

	m, err := migrate.NewWithDatabaseInstance(
		"file://database/migration",
		"mysql",
		driver,
	)
	if err != nil {
		log.Fatal("Error creating migration instance: ", err)
	}

	if err := m.Up(); err != nil && !errors.Is(err, migrate.ErrNoChange) {
		log.Fatal("Error applying migrations: ", err)
	}

	log.Println("Migrations applied successfully!")

	return nil
}
