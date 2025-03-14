FROM tomcat:latest

# Copiar el WAR al directorio de despliegue de Tomcat
COPY build/libs/EmployeeApp-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Exponer el puerto de Tomcat
EXPOSE 8080

# Iniciar Tomcat
CMD ["catalina.sh", "run"]