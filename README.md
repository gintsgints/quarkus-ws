# Quarkus WS example project

Project site - https://quarkus.io/

## Getting started

To start development start first start database:

```shell script
docker-compose up -d db
```

then start with mvn command:

```shell script
mvnw compile quarkus:dev
```

Then go 

http://localhost:8080/hello
http://localhost:8080/hello/greeting/type_your_name_here

## Testing

To test project you execute:

```shell script
mvnw test
```

## Building docker image

Docker image is build with command:

```shell script
docker build -t registry.balcia.com/balcia_dev/java/quarkus-ws .
```