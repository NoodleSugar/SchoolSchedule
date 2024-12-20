FROM ubuntu

RUN \
	apt update && \
	apt upgrade -y && \
	apt install -y \
	curl npm git openjdk-21-jdk-headless && \
    npm install -g @apideck/portman

EXPOSE 8080
EXPOSE 8888