FROM ubuntu:22.10

RUN apt update
RUN apt install -y openjdk-18-jdk
RUN apt install -y --no-install-recommends build-essential gcc
RUN apt install -y python3-pip
RUN apt install -y git
RUN pip3 install erdpy

CMD gunicorn --bind 0.0.0.0:$PORT wsgi

COPY target/*.jar app.jar
COPY ./earendil ./earendil
ENV PATH="~/root:${PATH}"

ENTRYPOINT ["java","-jar","/app.jar", "--server.port=${PORT}"]