# Usa imagem Eclipse Temurin Java 21 (compatível com o projeto)
FROM eclipse-temurin:21-jdk-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia o código-fonte para o container
COPY src/ ./src/

# Compila o projeto
RUN javac -d bin -sourcepath src \
    src/Main.java \
    src/model/*.java \
    src/repository/*.java \
    src/service/*.java \
    src/view/*.java \
    src/util/*.java

# Define o comando padrão para executar a aplicação
CMD ["java", "-cp", "bin", "Main"]
