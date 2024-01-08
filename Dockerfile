# Utiliza la imagen oficial de Maven para construir la aplicación
FROM maven:3.8.4-openjdk-17-slim AS builder

# Copia los archivos del proyecto al contenedor
COPY ./ /app
WORKDIR /app

# Compila la aplicación usando Maven
RUN mvn clean install -DskipTests

# Utiliza la imagen oficial de OpenJDK para ejecutar la aplicación
FROM openjdk:17-slim

# Copia el artefacto construido desde el contenedor de construcción al contenedor de ejecución
COPY --from=builder /app/target/sigconbackend-0.0.1-SNAPSHOT.jar /app/sigconbackend.jar

# Puerto en el que la aplicación Spring Boot escuchará las solicitudes
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "/app/sigconbackend.jar"]