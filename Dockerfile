FROM openjdk:17

WORKDIR /apps/lingventa-service

COPY target/*.jar lingventa-service.jar

EXPOSE 8080

ENV PROFILE=''

ENTRYPOINT java -Dspring.profiles.active=${PROFILE} -jar lingventa-service.jar
