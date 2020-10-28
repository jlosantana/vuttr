# vuttr - Very Useful Tool To Remember

base url (API Doc)

    http://localhost:3000/

endpoints

    http://localhost:3000/tools

## description

    Manager repository for useful tools.

## build

    mvn clean package

## run 

### springboot

    mvn spring-boot:run

### docker

    docker build -t vuttr/backend .
    docker run -it -p 3000:3000 vuttr/backend

## test

    mvn test

# technologies

    * Spring Boot
    * Spring Data
    * HSQL Database
    * Docker

