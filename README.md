# Clean2gather back-end application

## Installation

```sh
git clone git@github.com:Elorolf/cleantogather-api.git
cd cleantogather-api
docker-compose up -d
./mvnw spring-boot:run
```
### If you don't have docker-compose you need to launch a MySQL database and configure the env. variables in ``./src/main/resources/application.properties``

Runs the app in the development mode.<br />
Open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to view the rest api documentation.

## Learn More

This project was made with [Spring Boot](https://spring.io/projects/spring-boot).

To learn more about Spring Boot, check out:</br> 
[Spring Boot documentation](https://docs.spring.io/spring/docs/current/spring-framework-reference/)</br>
[Spring Boot guides](https://spring.io/guides)
