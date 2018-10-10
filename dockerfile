FROM tomcat:latest
MAINTAINER Felipe Mota
ADD ./ubhiya/target/ubhiya.war /usr/local/tomcat/webapps/
EXPOSE 8080
