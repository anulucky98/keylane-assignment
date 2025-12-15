FROM amazoncorretto:11-alpine3.22-jdk
MAINTAINER anulucky98
RUN addgroup --system apprunner && adduser --system apprunner --ingroup apprunner
COPY target/keylane-assignment-1.0.jar app.jar
USER apprunner:apprunner
ENTRYPOINT ["java", "-jar", "app.jar"]