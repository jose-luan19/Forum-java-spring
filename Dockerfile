# Estágio de build
FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app

# Copie o arquivo pom.xml e faça o download das dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie código fonte para o docker
COPY src ./src
RUN mvn package

# Estágio de produção
FROM openjdk:11-jre-slim
WORKDIR /app

# Copie o arquivo .jar construído no estágio anterior
COPY --from=build /app/target/*.jar .

# Expõe a porta em que a aplicação Spring Boot será executada e a porta para conectar o remote debug
EXPOSE 8080
EXPOSE 5005

# Comando para iniciar a aplicação Spring Boot com a porta 8000 habilitada para se conectar com remote debug da IDE
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-Xmx1G", "-Xms128m", "-XX:MaxMetaspaceSize=128m", "-jar", "forum.jar"]
