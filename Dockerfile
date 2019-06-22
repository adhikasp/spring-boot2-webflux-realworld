FROM azul/zulu-openjdk:8u212
MAINTAINER Adhika Setya Pramudita <adhika.setya.p@gmail.com>

RUN mvn clean package
