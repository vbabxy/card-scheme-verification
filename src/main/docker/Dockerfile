FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
WORKDIR /app
ADD @project.build.finalName@.jar /app

#execute the application
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","card-scheme-0.0.1-SNAPSHOT.jar"]