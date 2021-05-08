# back-sandbox-service

Spring boot project to display euro cities.

By default, it is using Spring Cloud config to load bootstrap properties. 

## Open Swagger UI web interface

    http://rest-ip:rest-port/swagger-ui.html
    
## Build Docker Image 

It is possible to build Docker image:

    ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=jandresmartinez/sandbox:latest  