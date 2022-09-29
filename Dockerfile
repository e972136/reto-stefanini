FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 1001
ARG JAR_FILE="*.jar"
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]