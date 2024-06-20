FROM eclipse-temurin:22-jdk

WORKDIR /app

COPY target/HotelManagmentSystem-0.0.1-SNAPSHOT.jar /app/hotel-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "hotel-app.jar"]