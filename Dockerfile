# Utiliza una imagen base adecuada para tu aplicación
FROM maven:3.9-amazoncorretto-17-alpine

# Crear un usuario no-root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el pom.xml para aprovechar el caché
COPY pom.xml pom.xml

# Copia el código fuente
COPY src src

# Cambia los permisos para que el usuario no-root pueda acceder a los archivos
RUN chown -R appuser:appgroup /app

# Instala las dependencias de Maven
RUN mvn dependency:go-offline

# Construye la aplicación
RUN mvn package

# Cambia al usuario no-root
USER appuser

# Exporta la aplicación como una imagen Docker
EXPOSE 8080
CMD ["java", "-jar", "target/*.jar"]
