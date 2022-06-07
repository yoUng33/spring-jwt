# For Java 8
FROM openjdk:8-jdk-alpine

# Refer to Maven build -> target jar name
ARG JAR_FILE=target/spring-jwt-0.0.1-SNAPSHOT.jar

# cd /opt/app/chegg_interview
WORKDIR /opt/app/chegg_interview

# cp target/spring-jwt-0.0.1-SNAPSHOT.jar /opt/app/chegg_interview/spring-jwt.jar
COPY ${JAR_FILE} spring-jwt.jar

# java -jar /opt/app/chegg_interview/spring-jwt.jar
ENTRYPOINT ["java","-jar","spring-jwt.jar"]