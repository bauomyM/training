FROM tomcat:9.0.62-jdk17-openjdk

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /usr/local/tomcat/webapps/app.jar

ENV SPRING_GRAPHQL_GRAPHIQL_ENABLED=true

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/local/tomcat/webapps/app.jar"]
