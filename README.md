Spring Boot Application Template/Starter-Project

Built With:

- Gradle - Dependency Management
- JDK - Java™ Platform, Standard Edition Development Kit
- Spring Boot - Framework to ease the bootstrapping and development of new Spring Applications
- MySQL - Open-Source Relational Database Management System
- git - Free and Open-Source distributed version control system
- Postman - API Development Environment (Testing Docmentation)

Running the application locally:

There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the main method in the com.souvenir_shop.Application class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse
- File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
- Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the Spring Boot gradle plugin like so:

souvenir_shop>gradlew bootRun
