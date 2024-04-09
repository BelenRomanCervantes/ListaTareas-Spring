FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:21
COPY --from=build /usr/src/app/target/ListaTareas-0.0.1-SNAPSHOT.jar /usr/app/ListaTareas-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/ListaTareas-0.0.1-SNAPSHOT.jar"]