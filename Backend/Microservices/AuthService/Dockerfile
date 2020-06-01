FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY /src/main/resources/init-db/1-authDBSchema.sql /docker-entrypoint-initdb.d/
COPY /src/main/resources/init-db/2-authDBData.sql /docker-entrypoint-initdb.d/
ADD /target/authService.jar authService.jar
ENTRYPOINT ["java","-jar","authService.jar"]

