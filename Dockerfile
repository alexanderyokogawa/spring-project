# ================================
# Stage 1: build
# ================================
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests clean package

# ================================
# Stage 2: runtime
# ================================
FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S app && adduser -S app -G app
WORKDIR /app

COPY --from=build /app/target/*jar app.jar
RUN chown -R app:app /app
USER app

ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -XX:+UseContainerSupport"
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
