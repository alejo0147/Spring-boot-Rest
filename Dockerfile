# IMAGEN MODELO
FROM eclipse-temurin:17.0.8_7-jdk AS build

# DEFINIR DIRECTORIO
WORKDIR /app

# COPIAR Y PEGAR ARCHIVOS
COPY ./pom.xml /app
COPY ./.mvn ./.mvn
COPY ./mvnw .

# DESCARGAR LAS DEPENDENCIAS
RUN ./mvnw dependency:go-offline
# RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

# COPIAR EL CÓDIGO FUENTE DENTRO DEL CONTENEDOR
COPY ./src ./src

# CONSTRUIR LA APLICACIÓN
RUN ./mvnw clean package -DskipTests

# IMAGEN FINAL PARA EJECUTAR LA APLICACIÓN
FROM eclipse-temurin:17.0.8_7-jdk

# DEFINIR EL DIRECTORIO DE TRABAJO
WORKDIR /app

# COPIAR EL ARCHIVO JAR CONSTRUIDO DESDE LA ETAPA ANTERIOR
COPY --from=build /app/target/SpringBootRest-0.0.1-SNAPSHOT.jar ./app.jar

# INFORMAR EL PUERTO
EXPOSE 8003

# LEVANTAR LA APLICACIÓN CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java", "-jar", "/app/app.jar"]