FROM openjdk:8
ADD target/AquaticStore-IR-TC.jar AquaticStore-IR-TC.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "AquaticStore-IR-TC.jar"]