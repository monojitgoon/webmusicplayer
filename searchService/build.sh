./gradlew shadowJar -PmainClass=de.uniba.dsg.jaxrs.SearchService -PjarName=SearchService.jar
cp build/libs/SearchService.jar dist
