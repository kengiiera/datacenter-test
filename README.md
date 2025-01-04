¡Por supuesto! A continuación, te proporciono el archivo `README.md` organizado para tu proyecto "Sistema de Recargas", incluyendo los comandos específicos para ejecutar tanto el backend desarrollado con **Spring Boot** como el frontend con **JSF** utilizando **Jetty**.

---

# Sistema de Recargas

**Descripción:**  
Este proyecto es un sistema de recargas que permite a los usuarios realizar recargas telefónicas y consultar estadísticas. Está desarrollado utilizando **Java Server Faces (JSF)** para el frontend y **Spring Boot** para el backend, con **MySQL** como base de datos y **Liquibase** para la gestión de cambios en la base de datos.

## Tabla de Contenidos

- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Requisitos Previos](#requisitos-previos)
- [Instalación y Despliegue](#instalación-y-despliegue)
  - [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
  - [Despliegue del Backend](#despliegue-del-backend)
  - [Despliegue del Frontend (JSF)](#despliegue-del-frontend-jsf)
- [Uso](#uso)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Características

- Realización de recargas a diferentes operadores.
- Consulta de estadísticas de recargas.

## Tecnologías Utilizadas

- **Backend:**
  - Java 11
  - Spring Boot
  - Maven
  - JPA/Hibernate
  - MySQL
  - Liquibase

- **Frontend:**
  - Java Server Faces (JSF)
  - PrimeFaces
  - HTML5/CSS3
  - Jetty

## Requisitos Previos

- **Java Development Kit (JDK) 11** o superior.
- **Apache Maven** 3.6.0 o superior.
- **MySQL** 5.7 o superior.
- **Jetty** 9.4 o superior.

## Instalación y Despliegue


1. **Configurar las credenciales de la base de datos:**

   Asegúrate de tener las credenciales correctas para acceder a `recargas_db`.
   Actualmente están configuradas la credenciales:
User: root
Password: root

### Despliegue del Backend


1. **Configurar la conexión a la base de datos:**

   Edita el archivo `src/main/resources/application.properties` y actualiza las propiedades de la base de datos:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/recargas_db
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```



4. **Construir y ejecutar el backend:**

   Compila y ejecuta la aplicación Spring Boot:

   ```bash
   mvn clean package
mvn spring-boot:run -X   
   ```
   Esto creará las tablas necesarias en `recargas_db`.
   El backend estará disponible en `http://localhost:8080`.

### Despliegue del Frontend (JSF)

1. **Configurar la conexión al backend:**

   Asegúrate de que las llamadas desde el frontend apunten a la URL correcta del backend (`http://localhost:8080`).

2. **Configurar Jetty para ejecutar la aplicación JSF:**

   En el archivo `pom.xml`, incluye el plugin de Jetty para Maven:

   ```xml
   <build>
       <plugins>
           <plugin>
               <groupId>org.eclipse.jetty</groupId>
               <artifactId>jetty-maven-plugin</artifactId>
               <version>9.4.35.v20201120</version>
               <configuration>
                   <scanIntervalSeconds>10</scanIntervalSeconds>
                   <webApp>
                       <contextPath>/</contextPath>
                       <descriptor>src/main/webapp/WEB-INF/web.xml</descriptor>
                       <resourceBase>src/main/webapp</resourceBase>
                   </webApp>
               </configuration>
           </plugin>
       </plugins>
   </build>
   ```

4. **Ejecutar la aplicación JSF con Jetty:**

   Utiliza Maven para iniciar Jetty y desplegar la aplicación:

   ```bash
   mvn jetty:run
   ```

   La aplicación estará disponible en `http://localhost:9090`.

## Uso

1. **Acceder a la aplicación:**

   - Backend: `http://localhost:8080`
   - Frontend: `http://localhost:9090`

   **Usuarios de prueba:**

   - **Vendedor:**  
     - Usuario: `vendedor1`  
     - Contraseña: `password1`

   - **Administrador:**  
     - Usuario: `admin`  
     - Contraseña: `password1`

2. **Realizar una recarga:**

   - Selecciona el operador.
   - Ingresa el número de teléfono y el monto de la recarga.
   - Confirma la operación.

3. **Consultar estadísticas:**

   - Accede a la sección de estadísticas para visualizar las recargas realizadas.


