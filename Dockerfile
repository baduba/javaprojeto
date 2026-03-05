# Multi-stage build para otimizar imagem Docker

# Stage 1: Build com Maven
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copia arquivos do projeto
COPY pom.xml .
COPY src ./src

# Compila o projeto
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o JAR compilado do stage anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Define variáveis de ambiente
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Comando para executar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
