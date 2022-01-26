FROM openjdk:10
EXPOSE 8080
ADD target/dichvucuoihoi-0.0.1-SNAPSHOT.jar dichvucuoihoi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/dichvucuoihoi-0.0.1-SNAPSHOT.jar"]