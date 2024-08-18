# Imagen base del JDK
FROM eclipse-temurin:17.0.8_7-jdk AS build

# Definir el directorio de trabajo
WORKDIR /app

# Copiar los archivos necesarios para construir la aplicación
COPY ./pom.xml ./
COPY ./.mvn ./.mvn
COPY ./mvnw ./

# Descargar las dependencias (no se requiere compilar aún)
RUN ./mvnw dependency:go-offline

# Copiar el código fuente
COPY ./src ./src

# Construir la aplicación
RUN ./mvnw clean package -DskipTests

# Imagen final para ejecutar la aplicación
FROM eclipse-temurin:17.0.8_7-jdk

# Definir el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR construido desde la etapa anterior
COPY --from=build /app/target/SpringBootrest-0.0.1-SNAPSHOT.jar ./app.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Definir el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
