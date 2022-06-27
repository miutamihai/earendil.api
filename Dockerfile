FROM openjdk:18.0.1.1

CMD gunicorn --bind 0.0.0.0:$PORT wsgi

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]