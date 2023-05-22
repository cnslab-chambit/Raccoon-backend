FROM amazoncorretto:11
ARG JAR_FILE=build/libs/raccoon-chatting-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dcom.amazonaws.sdk.disableEc2Metadata=true","-jar","app.jar"]







