FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /target/qaService.jar qaService.jar
ENTRYPOINT ["java","-jar","qaService.jar"]
