FROM chainguard/jdk

WORKDIR /app
COPY --chmod=777 ./build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT java -jar app.jar