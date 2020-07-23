# vuttr

base url (API Doc)

    http://localhost:3000/

endpoints

    http://localhost:3000/tools

## Startaideia - desafio back-end

    Feramenta de repositório para gerenciar ferramentas com seus respectivos nomes, links, descrições e tags.

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

