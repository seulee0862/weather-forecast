FROM azul/zulu-openjdk:17

ARG JAR_FILE=./build/libs

COPY ${JAR_FILE}/*-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=product","/app.jar"]