FROM golang:1.20 as build

WORKDIR /app

RUN apt-get update && apt-get install -y librdkafka-dev

# Copy the Go modules manifest and download dependencies
COPY go.mod .
COPY go.sum .
RUN go mod download

# Copy the source code into the container
COPY . .

# Build the Go application
RUN go build -o app ./cmd/walletcore

# Use a lightweight base image to create the final image
FROM golang:1.20-alpine

# Set working dir
WORKDIR /app

# Copy the compiled executable from the build image
COPY --from=build /app .

#CMD ["tail", "-f", "/dev/null"]

CMD ["./app"]