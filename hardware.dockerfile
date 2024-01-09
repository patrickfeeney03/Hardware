FROM amazoncorretto:19

WORKDIR /app

COPY target/hardware-1.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "hardware-1.0-SNAPSHOT.jar"]