FROM tomcat:latest
COPY target/demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/demo-0.0.1-SNAPSHOT
EXPOSE 8080
CMD ["catalina.sh", "run"]