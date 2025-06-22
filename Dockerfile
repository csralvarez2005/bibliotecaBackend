# Imagen base con Java 17 (ajusta si usas otra versión)
FROM eclipse-temurin:17-jdk

# Crea un directorio dentro del contenedor
WORKDIR /app

# Copia el archivo jar generado al contenedor
COPY target/biblioteca-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que usa tu app
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]