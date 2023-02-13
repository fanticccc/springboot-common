FROM java:8

COPY *.jar /app.jar

CMD ["--server.port=8089"]

EXPOSE 8089

ENTRYPOINT ["java","-jar","/app.jar"]