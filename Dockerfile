FROM eclipse-temurin:17-jdk-alpine

# 애플리케이션 JAR 파일 복사
COPY ./build/libs/*SNAPSHOT.jar airdnb.jar

# 시크릿 YAML 파일 복사
COPY ./src/main/resources/application.yml /config/secret.yml

# 엔트리포인트 설정
ENTRYPOINT ["java", "-jar", "-Dspring.config.location=/config/secret.yml", "-Dspring.profiles.active=prod", "airdnb.jar"]