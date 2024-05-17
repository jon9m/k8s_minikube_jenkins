FROM openjdk:11
EXPOSE 8080
ADD target/restapi-0.0.1-SNAPSHOT.jar restapi.jar
ENTRYPOINT [ "java", "-jar", "/restapi.jar" ]


