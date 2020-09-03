FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8083
ADD /build/libs/Lelang-Transaction-0.0.1-SNAPSHOT.jar Lelang-Transaction-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Lelang-Transaction-0.0.1-SNAPSHOT.jar"]