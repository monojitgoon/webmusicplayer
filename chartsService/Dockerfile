FROM openjdk:8-slim
COPY . /project
WORKDIR /project

RUN ./gradlew shadowJar -PmainClass=de.uniba.dsg.jaxrs.ChartsService -PjarName=ChartsService.jar
EXPOSE 8080

CMD ["java", "-jar" , "build/libs/ChartsService.jar"]


