FROM golang:1.20

WORKDIR /app

RUN apt-get update && apt-get install -y librdkafka-dev apt-utils

# Copy the Go modules manifest and download dependencies
COPY go.mod .
COPY go.sum .
RUN go mod download

# Copy the source code into the container
COPY . .

# Build the Go application
RUN go build -o app ./cmd/walletcore/main.go

CMD ["./app"]