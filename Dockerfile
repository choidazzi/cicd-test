FROM adoptopenjdk/openjdk11
ENV APP_HOME=/target
ARG JAR_FILE=/target/study.boot-0.0.1-SNAPSHOT.jar
WORKDIR $APP_HOME
COPY ${JAR_FILE} app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar", "app.jar"]
