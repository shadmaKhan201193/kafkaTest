FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
COPY ./build/libs/customerservice-0.0.1.jar usr/src/customerservice-0.0.1.jar
EXPOSE 8185
CMD ["java","-jar","usr/src/customerservice-0.0.1.jar"]