FROM openjdk:11-jre
MAINTAINER Sean "thisseanzhang@gmail.com"
WORKDIR server
COPY ./APIT-Server-0.0.2-beta.jar APIT-Server-0.0.2-beta.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "./APIT-Server-0.0.2-beta.jar"]