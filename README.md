# Cognitive Services - Content Moderator con Spring Boot
**Servicio Cognitivo de Azure**, implementado para evaluar imágenes **Ofensivo** o **No Ofensivo**, es decir, si la imagen tiene contenido subido de tono o para adultos, generando la API desde Azure Sandbox para consumirlo en Java.

**1. Cognitive Services**
<img src ="https://wakeupandcode.com/wp-content/uploads/2019/08/azure-cognitive-services-bootcamp-event-image.png" align="right" style="width: 200px"/>
- Azure - Content Moderator
- Evaluación de Imágenes: Ofensivo o No Ofensivo

**2. Spring Boot**
<img src ="https://miro.medium.com/v2/resize:fit:716/1*98O4Gb5HLSlmdUkKg1DP1Q.png" align="right" style="height:60px; width: 200px"/>
- Java: JDK 17
- IDE: IntelliJ IDEA 2020.3.2
- Maven: Apache Maven 3.8.1
- Server: Tomcat
- Frameworks: Spring Boot

**3. Maven Dependencias:**
<img src ="https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Apache_Maven_logo.svg/1280px-Apache_Maven_logo.svg.png" align="right" style="width: 200px"/>
* spring-boot-starter-webflux
* spring-boot-starter-data-r2dbc
* lombok
* reactor-test
* r2dbc-postgresql
* json
* okhttp

## **Dependencias Spring WebFlux + Postgre (SQL)**

Spring WebFlux | Data R2DBC | Project Reactor | R2DBC PostgreSQL
```
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-r2dbc</artifactId>
</dependency>
<dependency>
      <groupId>io.projectreactor</groupId>
      <artifactId>reactor-test</artifactId>
      <scope>test</scope>
</dependency>
<dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>r2dbc-postgresql</artifactId>
      <scope>runtime</scope>
</dependency>
```
## **Dependencias Spring WebFlux + MongoDB (NoSQL)**
```
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
</dependency>
<dependency>
      <groupId>io.projectreactor</groupId>
      <artifactId>reactor-test</artifactId>
      <scope>test</scope>
</dependency>
```
## **Dependencias Swagger para WebFlux**
```
<dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
      <version>2.0.2</version>
</dependency>
```
