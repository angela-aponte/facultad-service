FROM openjdk:23
LABEL authors="Usuario"
WORKDIR /app
COPY target/Facultad-Service-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "Facultad-Service-0.0.1-SNAPSHOT.jar" ]