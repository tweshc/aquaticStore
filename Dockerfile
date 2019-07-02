FROM openjdk:8
ADD target/aquaticstore.jar aquaticstore.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "aquaticstore.jar"]