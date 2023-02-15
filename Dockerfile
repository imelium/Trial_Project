FROM openjdk:17-jdk-alpine
MAINTAINER Nikita Avtukhov
COPY target/trialproject-1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]