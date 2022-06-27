FROM ubuntu:22.10

RUN apt update
RUN apt install -y openjdk-18-jdk
RUN apt install -y --no-install-recommends build-essential gcc
RUN apt install -y python3-pip
RUN pip3 install erdpy

CMD gunicorn --bind 0.0.0.0:$PORT wsgi

RUN useradd --create-home earendil

USER earendil

COPY target/*.jar app.jar
COPY ./earendil ./earendil
ENV PATH="~/earendil:${PATH}"

ENTRYPOINT ["java","-jar","/app.jar", "--server.port=${PORT}"]