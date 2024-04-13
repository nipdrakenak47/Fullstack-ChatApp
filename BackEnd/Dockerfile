FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
copy --from=build /target/chatappbackend-0.0.1-SNAPSHOT.jar chatappbackend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","chatappbackend.jar"]