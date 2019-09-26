FROM openjdk:8u222 AS build
WORKDIR /usr/local/app

COPY . .

RUN ./mvnw clean package -Dmaven.test.skip=true

FROM fabric8/java-alpine-openjdk8-jre AS release
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV AB_ENABLED=jmx_exporter

COPY --from=build /usr/local/app/target/lib/* /deployments/lib/
COPY --from=build /usr/local/app/target/*-runner.jar /deployments/app.jar

HEALTHCHECK --interval=1m --timeout=3s --start-period=15s \
  CMD wget -qO- http://localhost:8080 &> /dev/null || exit 1

EXPOSE 8080

ENTRYPOINT [ "/deployments/run-java.sh" ]