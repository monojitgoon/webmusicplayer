
#Docker:
 
For the docker-part we made a Dockerfile foreach Service in its directory. 
The Imageservice will run on Tomcat. 
The Chartsservice will be build in its dockerfile,
the artistServices jar will just be copied. 

#Apis:

The api documentation can be found for each service in its directory as swagger.yaml. Made with http://editor.swagger.io/ 

api.js


Run with docker-compose:

 docker-compose up --build
