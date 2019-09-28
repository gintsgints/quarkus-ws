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

If you are going to work with secured endpoints, you have to start keycloak server:

```shell script
docker-compose up -d keycloak
export KEYCLOAK_SERVER=localhost:8180
# vai windows
set KEYCLOAK_SERVER=localhost:8180
```

then start with mvn command:

```shell script
mvnw compile quarkus:dev -Dquarkus.profile=dev
```

Then go 

http://localhost:8080/hello
http://localhost:8080/hello/greeting/type_your_name_here
http://localhost:8080/fruits/

Also you can check swagger here:

http://localhost:8080/swagger-ui

Some test commands:

```shell script
curl -X POST "http://wsexample.dev.balcia.com:30310/fruits" -H "accept: application/json" -H "Content-Type: application/json" -d '{"name": "First fruit"}'
curl -X GET "http://wsexample.dev.balcia.com:30310/fruits/{id_you_got_form_previous}" -H "accept: application/json"
```

To test secured endpoints:

```shell script
# Get token from keycloak server (you have to have jq app at your path https://stedolan.github.io/jq/
# alice normal user
export access_token=$(\
    curl -X POST http://localhost:8180/auth/realms/balcia-test/protocol/openid-connect/token \
    --user backend-service:secret \
    -H 'content-type: application/x-www-form-urlencoded' \
    -d 'username=alice&password=alice&grant_type=password' | ./jq --raw-output '.access_token' \
 )
# Check token
echo $access_token
# Access different points

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
