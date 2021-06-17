# job4j_tracker

[![Build Status](https://travis-ci.org/amasterenko/job4j_tracker.svg?branch=master)](https://travis-ci.org/amasterenko/job4j_tracker)
[![codecov](https://codecov.io/gh/amasterenko/job4j_tracker/branch/master/graph/badge.svg?token=DZRHXYXU00)](https://codecov.io/gh/amasterenko/job4j_tracker)  
___  
This is a Java learning project. It demonstrates CRUD operations on items.   
#### Used technologies
* Java Core  
* JUnit  
* Maven
* Hibernate  
* PostgreSQL
* LiquiBase
* Travis C.I.
* JaCoCo
* Codecov

#### Usage  
1. Create db _tracker_, specify db settings in _pom.xml_ and _app.properties_.
2. Build the project: ```mvn clean package``` 
3. Run the app: ```java -jar target/tracker.jar```  
#### User interface  
![ScreenShot](img/tracker.png) 
