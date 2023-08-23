FROM azul/zulu-openjdk:17

ARG JAR_FILE=./build/libs

COPY ${JAR_FILE}/*.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","/app.jar"]