# Test assignment «Task Tracker» v2
## [Start application locally with Docker](#docker-run)
## [Start application locally](#local-run)
## [API testing with Swagger](#swagger)
<a name="docker-run"></a> 
1. Clone the repository:
```
git clone https://github.com/solosuic1de/task-tracker
``` 
2. Install latest version of the Docker
3. To start application locally execute the following command from the root directory of the project:
```
docker-compose up --build
```  
4. To stop application artifacts execute the following command from the root directory of the project:
```
docker-compose up --down
```
5. To test API go to [localhost:8080/v1/](http://localhost:8080/v1/)

<a name="local-run"></a> 
## Start application locally
### Software
Software required to run this application:
1. Java 14 (you can change the version in ```build.gradle```)
2. PostgreSQL Server
3. Gradle

### Configuration
The application configure the connection in the file ```application-local.properties```
#### Database Configuration:
* DATABASE_NAME - name of database to be used by application
* DATABASE_USER - username used to access database
* DATABASE_PASSWORD - password used to access database

### Run application
1. Clone the repository:
```
git clone https://github.com/solosuic1de/task-tracker
``` 
2. To build and run application execute the following command from the root directory of the project:

```
gradle bootRun -Plocal
```
3. To test API go to [localhost:8080/v1/](http://localhost:8080/v1/)
<a name="swagger"></a> 
## API testing with Swagger
1. After you launched the application, open [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)
2. You can register new user using ```/v1/auth/register``` or use users who were automatically added to the database from the file ```src/main/resources/db/migration/V1.2__fill_tables.sql```
2. To log in, copy token who will return after execution ```/v1/auth/login```
3. Click on Authorize button, and paste in value field next: ```Bearer copied_token```. See an example [here](https://i.stack.imgur.com/NXpd1.png)
