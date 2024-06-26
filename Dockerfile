FROM tomcat:latest
ADD /target/demo-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps
EXPOSE 8080
