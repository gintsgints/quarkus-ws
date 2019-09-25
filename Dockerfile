FROM quay.io/quarkus/centos-quarkus-maven:19.1.1 AS build
WORKDIR /usr/local/app

COPY . /usr/local/app
RUN ./mvnw -Pnative clean package

FROM registry.access.redhat.com/ubi8/ubi-minimal AS release
WORKDIR /usr/local/app

LABEL maintainer="Gints Polis <gints.polis@balcia.com>"
LABEL description="REST API application"

RUN groupadd -r app && useradd -r -gapp app
RUN mkdir -m 0755 -p /usr/local/app/bin
RUN mkdir -m 0755 -p /usr/local/app/config
RUN mkdir -m 0755 -p /usr/local/app/logs/

COPY --from=build /usr/src/app/target/*-runner /usr/local/app/application

RUN chown -R app:app /usr/local/app

EXPOSE 8080

HEALTHCHECK --interval=1m --timeout=3s --start-period=15s \
  CMD wget -qO- http://localhost:8080 &> /dev/null || exit 1

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]

