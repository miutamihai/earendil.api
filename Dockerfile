FROM ubuntu:22.10

RUN apt update
RUN apt install -y gnupg
RUN apt install -y wget
RUN apt install -y openjdk-18-jdk
RUN apt install -y --no-install-recommends build-essential gcc
RUN apt install -y python3-pip
RUN apt install -y git
RUN pip3 install erdpy

RUN apt update
RUN apt install -y curl software-properties-common apt-transport-https lsb-release
RUN curl -fsSL https://packages.erlang-solutions.com/ubuntu/erlang_solutions.asc | gpg --dearmor -o /etc/apt/trusted.gpg.d/erlang.gpg

RUN apt update -y
RUN apt install -y erlang
RUN apt install -y elixir

CMD gunicorn --bind 0.0.0.0:$PORT wsgi

COPY target/*.jar app.jar
COPY ./earendil ./earendil

ENTRYPOINT ["java","-jar","/app.jar", "--server.port=${PORT}"]