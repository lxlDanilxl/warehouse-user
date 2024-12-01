# Utiliza una imagen base adecuada para tu aplicación
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el pom.xml para aprovechar el caché
COPY pom.xml pom.xml

# Copia el código fuente
COPY src src

# Instala las dependencias de Maven
RUN mvn dependency:go-offline

# Construye la aplicación
RUN mvn package

# Exporta la aplicación como una imagen Docker
EXPOSE 8080
CMD ["java", "-jar", "target/*.jar"]