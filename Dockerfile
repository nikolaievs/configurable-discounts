FROM amazoncorretto:17-alpine-jdk

COPY build/libs/configurable-discounts-0.0.1-SNAPSHOT.jar configurable-discounts.jar

ENTRYPOINT ["java","-jar","/configurable-discounts.jar"]

EXPOSE 8080 8081