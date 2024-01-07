FROM ubuntu:latest AS build 
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN ./maven bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=target /target/sigconbackend-0.0.1-SNAPSHOT.jar sigconbackend.jar
                              

ENTRYPOINT ["java","-jar","sigconbackend.jar"]