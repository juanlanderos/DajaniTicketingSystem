![Logo](https://dajanico.com/wp-content/uploads/2022/02/Dajanico-Logo.png)

# Dajani Ticketing System

## General Info
This is a ticketing system built to provide Dajani Consulting Inc. with a means of establishing a help desk and keep track of issues.

## Technologies
Project is created with:
* Java 11
* Spring Boot 2.6.6
* MySQL 8.0.26
* Angular 14 + Bootstrap 5 CSS

## Testing
- REST services tested via Postman
- Frontend testing tools: Jasmine & Karma

## Deployment
- Create environment in AWS Elastic Beanstalk, and package backend app as .war
- Deploy the .war to this environment; AWS RDS instance can be configured here or seperately in RDS console, just update the datasource properties to match

## Developer Instructions
- Security: replace email config, datasource, and port number in application.properties to desired values

## Documentation
- [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
