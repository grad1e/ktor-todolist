FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/
WORKDIR /app
ENTRYPOINT java -DDATABASE_HOST=${DATABASE_HOST} -DDATABASE_PORT=${DATABASE_PORT} -DDATABASE_NAME=${DATABASE_NAME} -DDATABASE_USER=${DATABASE_USER} -DDATABASE_PASSWORD=${DATABASE_PASSWORD} -DDEVELOPMENT=${DEVELOPMENT} -jar dev.daryl.ktor-todolist-all.jar