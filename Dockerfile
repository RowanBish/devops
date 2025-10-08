FROM openjdk:latest
COPY .0.1.0.2 0.1-alpha-2jar-with-dependencies.jar
WORKDIR /tmp
ENTRYPOINT ["java", "0.1.0.2 (0.1-alpha-2"]