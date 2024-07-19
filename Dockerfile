FROM eclipse-temurin:17.0.11_9-jdk

MAINTAINER uniquindio
EXPOSE 8080

WORKDIR /root

COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY ./src /root/src
COPY target/inventarioApi-0.0.1-SNAPSHOT.jar /root/target/app.jar

RUN ./mvnw clean install -DskipTests

ENTRYPOINT ["java","-jar","/root/target/app.jar"]