# FROM gradle:jdk8-alpine
FROM openjdk:8-jdk-alpine

VOLUME /tmp
RUN mkdir /app
COPY . /app
WORKDIR /app

RUN /app/gradlew build --full-stacktrace
RUN mv /app/build/libs/*.jar /app/demo.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/demo.jar"]
