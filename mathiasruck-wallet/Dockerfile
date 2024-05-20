FROM maven:3.9.6-amazoncorretto-21 as build

WORKDIR /app

COPY . /app

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jammy

VOLUME /tmp

ARG JAR_FILE=/app/target/*.jar

COPY --from=build ${JAR_FILE} /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]