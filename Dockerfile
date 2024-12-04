# Utiliza una imagen base adecuada para tu aplicación
FROM maven:3.9-amazoncorretto-17-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Crea un nuevo usuario con un UID no root
RUN adduser -D -u 1001 appuser

# Cambia la propiedad de los directorios necesarios para el nuevo usuario
RUN chown -R appuser:appuser /app

RUN mkdir /root/.m2

RUN chown -R appuser:appuser /root/.m2

RUN echo "<localRepository>C:\Users\me\.m2\repo</localRepository>" >> settings.xml

# Establece que el contenedor se ejecute como este nuevo usuario no root
USER appuser

# Configura Maven para que use el directorio de trabajo /app para sus archivos generados
ENV MAVEN_OPTS="-Dmaven.repo.local=/app/.m2"

# Copia el pom.xml para aprovechar el caché
COPY pom.xml pom.xml

# Copia el código fuente
COPY src src

# Instala las dependencias de Maven
RUN mvn dependency:go-offlin

# Construye la aplicación y mueve los archivos generados a /app
RUN mvn package -DskipTests

# Mueve los archivos generados a /app
RUN mv /app/target/*.jar /app/


# Exporta la aplicación como una imagen Docker
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/*.jar"]
