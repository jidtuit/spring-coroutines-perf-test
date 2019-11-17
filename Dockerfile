#FROM gradle:6.0.0-jdk13 as GRADLE
FROM adoptopenjdk:13-jdk-hotspot as BUILD
WORKDIR /home/app
COPY ./ ./
RUN ./gradlew clean spring-coroutines:bootJar

FROM adoptopenjdk:13-jre-hotspot
EXPOSE 8080
WORKDIR /home/app
COPY --from=BUILD /home/app/spring-coroutines/build/libs/*.jar ./sbCoroutines/app.jar
ENTRYPOINT ["java", "-jar", "sbCoroutinDOCKes/app.jar"]