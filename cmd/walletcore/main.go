package walletcore

import (
	"database/sql"
	"fmt"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/database"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/event"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/usecase/create_account"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/usecase/create_client"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/usecase/create_transaction"
	"github.com.br/mathiasruck/fc-ms-wallet/pkg/events"
	_ "github.com/go-sql-driver/mysql"
)

func main() {
	db, err := sql.Open("mysql", fmt.Sprintf("%s:%s@tcp(%s:%s)/%s?charset=utf8&parseTime=True&loc=Local", "root", "root", "mysql", "3306", "wallet"))
	if err != nil {
		panic(err)
	}

	defer db.Close()

	eventDispatcher := events.NewEventDispatcher()
	transactionCreatedEvent := event.NewTransactionCreated()
	//eventDispatcher.Register("TransactionCreated", handler)

	clentDb := database.NewClientDB(db)
	accountDb := database.NewAccountDB(db)
	transactionDb := database.NewTransactionDB(db)

	createClientUseCase := create_client.NewCreateClientUseCase(clentDb)
	createAccountUseCase := create_account.NewCreateAccountUseCase(accountDb, clentDb)
	createTransactionUseCase := create_transaction.NewCreateTransactionUseCase(transactionDb, accountDb, eventDispatcher, transactionCreatedEvent)
}
