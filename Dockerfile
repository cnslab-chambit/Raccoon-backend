FROM amazoncorretto:11 
ARG JAR_FILE=raccoon-api/build/libs/raccoon-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar 
ENTRYPOINT ["java","-Dcom.amazonaws.sdk.disableEc2Metadata=true","-jar","app.jar"] 
 
 
 
 
 
 
