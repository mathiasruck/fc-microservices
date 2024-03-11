package create_transaction

import (
	"context"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/entity"
	"github.com.br/mathiasruck/fc-ms-wallet/internal/event"
	"github.com.br/mathiasruck/fc-ms-wallet/pkg/events"
	"github.com.br/mathiasruck/fc-ms-wallet/pkg/uow"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/mock"
	"testing"
)

type UowMock struct {
	mock.Mock
}

func (m *UowMock) Register(name string, fc uow.RepositoryFactory) {
	m.Called(name, fc)
}

func (m *UowMock) GetRepository(ctx context.Context, name string) (interface{}, error) {
	args := m.Called(name)
	return args.Get(0), args.Error(1)
}

func (m *UowMock) Do(ctx context.Context, fn func(uow *uow.Uow) error) error {
	args := m.Called(fn)
	return args.Error(0)
}

func (m *UowMock) CommitOrRollback() error {
	args := m.Called()
	return args.Error(0)
}

func (m *UowMock) Rollback() error {
	args := m.Called()
	return args.Error(0)
}

func (m *UowMock) UnRegister(name string) {
	m.Called(name)
}

type TransactionGatewayMock struct {
	mock.Mock
}

func (m *TransactionGatewayMock) Create(transaction *entity.Transaction) error {
	args := m.Called(transaction)
	return args.Error(0)
}

type AccountGatewayMock struct {
	mock.Mock
}

func (m *AccountGatewayMock) Save(account *entity.Account) error {
	args := m.Called(account)
	return args.Error(0)
}

func (m *AccountGatewayMock) FindByID(id string) (*entity.Account, error) {
	args := m.Called(id)
	return args.Get(0).(*entity.Account), args.Error(1)
}

func TestCreateTransactionUseCase_Execute(t *testing.T) {
	client1, _ := entity.NewClient("client1", "j@j.com")
	account1 := entity.NewAccount(client1)
	account1.Credit(1000)

	client2, _ := entity.NewClient("client2", "j2@j.com")
	account2 := entity.NewAccount(client2)
	account2.Credit(1000)

	mockUow := &UowMock{}
	mockUow.On("Do", mock.Anything, mock.Anything).Return(nil)

	inputDto := CreateTransactionInputDTO{
		AccountIDFrom: account1.ID,
		AccountIDTo:   account2.ID,
		Amount:        100,
	}

	dispatcher := events.NewEventDispatcher()
	evento := event.NewTransactionCreated()
	ctx := context.Background()

	uc := NewCreateTransactionUseCase(mockUow, dispatcher, evento)
	output, err := uc.Execute(ctx, inputDto)

	assert.Nil(t, err)
	assert.NotNil(t, output)
	mockUow.AssertExpectations(t)
	mockUow.AssertNumberOfCalls(t, "Do", 1)
}
