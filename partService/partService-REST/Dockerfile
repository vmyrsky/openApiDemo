# syntax=docker/dockerfile:1

FROM openjdk:16-alpine3.13
WORKDIR /springboot-docker

COPY ./target/springboot-docker.jar ./

EXPOSE 8080

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "springboot-docker.jar"]