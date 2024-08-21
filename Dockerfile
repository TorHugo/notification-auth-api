FROM openjdk:21

WORKDIR /app

COPY build/libs/notification-auth-api.jar /app/notification-auth-api.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "notification-auth-api.jar"]

# docker build -t [username]/[image_name]:[version] .
# docker push [username]/[image_name]:[version]