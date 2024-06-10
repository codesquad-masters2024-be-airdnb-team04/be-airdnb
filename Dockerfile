FROM eclipse-temurin:17-jdk-alpine
COPY ./build/libs/*SNAPSHOT.jar airdnb.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "airdnb.jar"]