FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /target/authService.jar authService.jar
ENTRYPOINT ["java","-jar","authService.jar"]

