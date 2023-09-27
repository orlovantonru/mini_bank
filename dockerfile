
FROM openjdk:17-jdk-slim-buster AS builder

WORKDIR /app
COPY . .

RUN chmod a+x mvnw && \
./mvnw package

FROM openjdk:17-jdk-slim-buster
COPY --from=builder /app/target/*.jar /app

WORKDIR /app
ENTRYPOINT java -jar demo-*.jar
