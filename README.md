# Clean2gather back-end application

## Installation

```sh
git clone git@github.com:Elorolf/cleantogather-api.git
cd cleantogather-api
docker-compose up -d
```
The `docker-compose up -d` command starts 2 containers : a MySQL database already configured with the good env. variables and a phpmyadmin container accessible on [http://localhost:8081/](http://localhost:8081/) with the following identifiers : user = "user" ; password = "password". <br /> <br/>
If you don't have docker and docker-compose installed on your machine you need to launch a MySQL database and configure the env. variables in ``./src/main/resources/application.properties`` like this :

```sh
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:0.0.0.0}:3306/cleantogather
spring.datasource.username=user
spring.datasource.password=password
```

After launched the database you can start the api service :
```sh
./mvnw spring-boot:run
```

That's it the API started in development mode.<br />

Open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to view the REST api documentation.

## Learn More

This project was made with [Spring Boot](https://spring.io/projects/spring-boot).

To learn more about Spring Boot, check out:</br> 
[Spring Boot documentation](https://docs.spring.io/spring/docs/current/spring-framework-reference/)</br>
[Spring Boot guides](https://spring.io/guides)
