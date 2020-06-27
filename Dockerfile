FROM gradle:jre14 AS GRADLE_TOOL_CHAIN
COPY build.gradle /tmp/
COPY settings.gradle /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN gradle build



FROM openjdk:14-alpine
COPY --from=GRADLE_TOOL_CHAIN /tmp/build/libs/tasktracker-0.0.1-SNAPSHOT.jar tasktracker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "tasktracker.jar"]