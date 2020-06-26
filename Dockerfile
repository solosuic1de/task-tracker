FROM openjdk:14-alpine
LABEL maintainer="dmytro.krutii71@gmail.com"
VOLUME /tmp
EXPOSE 8080
ADD build/libs/tasktracker-0.0.1-SNAPSHOT.jar tasktracker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tasktracker.jar"]