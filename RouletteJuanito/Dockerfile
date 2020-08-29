FROM alpine:edge
RUN apk add --no-cache openjdk11
COPY ./target/roulette-0.1.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch roulette-0.1.jar'
ENTRYPOINT ["java","-jar","roulette-0.1.jar"]
