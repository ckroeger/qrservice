FROM eclipse-temurin:18-jdk-alpine
ARG app_name=qrservice
RUN echo "Building app with ${app_name}"
COPY target/${app_name}*.jar /app.jar
CMD java $JAVA_OPTS -Dspring.profiles.active=prod -jar /app.jar
EXPOSE 8080 8080