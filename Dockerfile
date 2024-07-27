FROM openjdk:21

WORKDIR /app

COPY build/libs/auth-service-api.jar /app/auth-service-api.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "auth-service-api"]

# docker build -t [username]/[image_name]:[version] .
# docker push [username]/[image_name]:[version]