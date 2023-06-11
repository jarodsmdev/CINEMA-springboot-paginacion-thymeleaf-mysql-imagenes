# CINEMA-springboot-paginacion-thymeleaf-mysql-imagenes

## Tecnologías utilizadas

- Spring Boot
- Thymeleaf
- MySQL
- Fancybox

## Descripción del proyecto

Este proyecto es una aplicación web desarrollada utilizando Spring Boot y Thymeleaf. Permite la paginación de películas y utiliza una base de datos MySQL para almacenar la información. Además, se utiliza el plugin Fancybox para mostrar los trailers y la portada de las películas.

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- Java 8 o superior
- MySQL

## Configuración

1. Clona el repositorio:

   
   [git clone https://github.com/jarodsmdev/CINEMA-springboot-paginacion-thymeleaf-mysql-imagenes.git
](https://github.com/jarodsmdev/CINEMA-springboot-paginacion-thymeleaf-mysql-imagenes.git
)
   

2. Accede al directorio del proyecto:

   
   cd tu-proyecto
   

3. Configura la base de datos:

   - Crea una base de datos en MySQL (Se agrega Script en el directorio `BBDD/ScriptBBDD.sql`.
   - Actualiza el archivo `application.properties` con la información de tu base de datos.

4. Compila y ejecuta el proyecto:

   
   ./mvnw spring-boot:run
   

5. Abre un navegador web y accede a `http://localhost:8080` para ver la aplicación en funcionamiento.

## Funcionalidades principales

- Paginación de películas: Permite navegar a través de las diferentes páginas de películas disponibles.
- Fancybox: Muestra los trailers y la portada de las películas utilizando el plugin Fancybox.

## Contribuciones

Las contribuciones son bienvenidas. Si encuentras algún error o tienes alguna mejora, no dudes en abrir un pull request.
