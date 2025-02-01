FROM tomcat:9.0

WORKDIR /usr/local/tomcat/webapps

COPY target/clever-climb-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
