# Explore California Backend API with Spring Boot
Backend API for [explorecalifornia](https://explorecalifornia.org) written with Java using the Spring framework.
## How to run it?
First build with:
```markdown
mvn clean package
```
Then run like this:
```markdown
java -jar ./target/explorecali-VERSION.jar
```
This runs the server on localhost:8080, just enter the page, and you will see the links that enable the API use. <br/>
If you want to see an overview of the API enter this on your browser: <br/>
```markdown
http://localhost:8080/browser/index.html
```
## IMPORTANT
To run the app you need a postgresql instance with a explorecali db running on the system. <br/>
To use a postgres instance change the **application.properties** file to something like this:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/explorecali
spring.datasource.username=postgres
spring.datasource.password=postgres
```