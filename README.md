# Quarkus WS example project

Framework project site - https://quarkus.io/

## Working example

All code is automaticaly deployed on development environment.
To access it you have to modify your local hosts file and add entry:

```shell script
10.203.8.21 wsexample.dev.balcia.com
```

then go http://wsexample.dev.balcia.com:30310/

## Getting started

To start development start first start database:

```shell script
docker-compose up -d db
```

then start with mvn command:

```shell script
mvnw compile quarkus:dev -Dquarkus.profile=dev
```

Then go 

http://localhost:8080/hello
http://localhost:8080/hello/greeting/type_your_name_here
http://localhost:8080/fruits/

Some test commands:

```shell script
# Not fully working yet....
curl -X POST "http://wsexample.dev.balcia.com:30310/fruits" -H "accept: application/json" -H "Content-Type: application/json" -d '{"name": "First fruit"}'
curl -X GET "http://wsexample.dev.balcia.com:30310/fruits/{id_you_got_form_previous}" -H "accept: application/json"
```

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

then you start whole project by docker compose:

```shell script
docker-compose up -d
```
