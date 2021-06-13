# Pre-test Home Credit

Backend Services for pre-test Home Credit

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites

What things you need to install the software and how to install them

```
- Maven
- OpenJDK 8
- MySql
```

### Preparation

First you should set application properties with your configuration. 

```
- sql.host = your host sql
- spring.datasource.username = your database username
- spring.datasource.password = your database password
- sql.database-name = your database name
- server.port = you port that you want to used
```

### Installing

A step to get build jar

```
mvn clean install
```

### Run

A step for running this service

```
java -jar target/application.jar
```

## Running Swagger
You can access http://{your-port-service}/swagger-ui.html for view API Documentation using Swagger

## Running The Tests

Explain how to run the automated tests for this system

```
mvn clean test
```