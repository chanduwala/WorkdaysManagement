FROM java:8
EXPOSE 8080
ADD /target/WorkdaysManagement-0.0.1-SNAPSHOT.jar workdays-management-0.0.1.jar
ENTRYPOINT ["java", "-jar", "workdays-management-0.0.1.jar"]
