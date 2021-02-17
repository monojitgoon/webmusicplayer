./gradlew war
cp build/libs/imageService.war dist/
docker build --no-cache -t group9/imageservice .
docker run -it --rm -p 8080:8080 group9/imageservice
