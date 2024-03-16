package handler

import (
	"fmt"
	"github.com.br/mathiasruck/fc-ms-wallet/pkg/events"
	"github.com.br/mathiasruck/fc-ms-wallet/pkg/kafka"
	"sync"
)

type UpdateBalanceKafkaHandler struct {
	Kafka *kafka.Producer
}

func NewUpdateBalanceKafkaHandler(kafka *kafka.Producer) *UpdateBalanceKafkaHandler {
	return &UpdateBalanceKafkaHandler{
		Kafka: kafka,
	}
}

func (h *UpdateBalanceKafkaHandler) Handle(message events.EventInterface, wg *sync.WaitGroup) {
	defer wg.Done()
	h.Kafka.Publish(message, nil, "balances")
	fmt.Println("UpdateBalanceKafkaHandler called")
}
