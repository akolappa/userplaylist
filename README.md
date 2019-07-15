#User Playlist

##Functionalities

* Search for an article by keyword in the search api exposed.
* CRUD operations for playlist.
* Adding, Deleting and enriching the articles in a playlist.

##Instructions to package and run

* Use maven for packaging and build `mvn clean package`
* First update the authorization key for the external api in the application.properties `externalapi.authorization` 
* Navigate to the target folder and you will find the packaged spring boot jar to run use `java -jar userplaylist-0.0
.1.jar`
* DML scripts are already included in the classpath and db tables will automatically created by JPA.
* Basic Documentation/try outs of the api can be found in [User playlist swagger](http://localhost:9090/v1/api/swagger-ui.html#/)
* Design of the apis can be found in [designs](designs) folder in the root folder.
* In memory H2 database is used for the project, you can find the console for the db [here](http://localhost:9090/v1/api/h2-console). 
