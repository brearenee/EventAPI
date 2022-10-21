
## Spring Boot Event Application
### A Spring Boot Microservice API project using PostgresSQL



<br>
<b>Created By:</b> <br>
Brea Chaney<br>
Nathan Mead



<b>versions:</b> <br>
JDK version 17.0.4.1<br>
Springboot 2.7.3<br>
Apache Maven 3.0.5<br>
Docker version 20.10.17<br>

### Steps To Run Locally:


clone repository

Install Docker if you have not already done so. 
	
run command:

    cd scripts ./start container
	
run command in another terminal:

	cd scripts ./attach-container
	
run command in same terminal as above:

    mvn clean install
	 
Run application using:

     mvn spring-boot:run
	

The web application is accessible via browser at http://localhost:8080/

## Heroku
also available via cloud at<br>
https://cs-3250-project-1-nmead1-brea.herokuapp.com/countries
  