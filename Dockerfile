FROM openjdk:17-alpine

WORKDIR /app

COPY . .

COPY "user-accounts/target/user-accounts-1.0.0.jar" app.jar

EXPOSE 7070

ENTRYPOINT ["java", "-jar", "app.jar"]
