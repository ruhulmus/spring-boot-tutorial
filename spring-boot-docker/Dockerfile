# For Java 11, try this
#FROM adoptopenjdk/openjdk11:alpine-jre

# For Java 8, try this
FROM openjdk:8-jdk-alpine

# cd /opt/app
WORKDIR /opt/app

# Refer to Maven build -> finalName
ARG JAR_FILE=target/spring-boot-docker-0.0.1-SNAPSHOT.jar

# cp target/spring-boot-docker-0.0.1-SNAPSHOT.jar /opt/app/dockerapp.jar
COPY ${JAR_FILE} dockerapp.jar

# java -jar /opt/app/dockerapp.jar
ENTRYPOINT ["java","-jar","dockerapp.jar"]

## sudo docker build -t spring-boot-docker:1.0 .
## sudo docker run -d -p 8090:8089 -t spring-boot-docker:1.0
## sudo docker run -p 8090:8089 -t spring-boot-docker:1.0
## sudo docker run -p 80:8080 -t spring-boot-docker:1.0
## sudo docker run -p 443:8443 -t spring-boot-docker:1.0
## sudo docker image tag spring-boot-docker:1.0 ruhulmus/spring-boot-docker:1.0