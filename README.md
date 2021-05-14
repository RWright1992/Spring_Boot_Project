src/main/java coverage: 92.4%
# Hobby Web Application (HWA)

A Spring Boot web application used for managing a collection of music. You can create, read, update and delete music from the database using a front-end web page.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

#### Java 

Go to this link [download JDK.] (https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)
Click the Download link that corresponds to the .exe for your version of Windows.

```e.g. jdk-16.0.1_windows-x64_bin.exe```

Run the installer with admin priveleges and follow the instructions.
Check if the installer set the *PATH* veriable for you, as JDK15+ does this automatically.

To set the *PATH* variable, browse to **Control Panel** and then **System.** 
Click **Advanced** and then **Environment Variables.**
Add the location of the *bin* folder of your JDK installation to the **PATH** variable in **System Variables.**
Typically, the full path is:
*C:\Program Files\Java\jdk-16\bin*

To verify your JDK installation, launch a command prompt window and type:

```javac - version```

And

```java -version```

If you don't get a successful response from these commands, you will need to return to the installation process.

#### Maven

Go to this link [download Maven.] (https://maven.apache.org/download.cgi)
Click the Binary zip archive download under 'Files'.

```e.g. apache-maven-3.8.1-bin.zip```

Once that has downloaded, unzip it into your C:\Program Files folder.
Copy the path for the installation.

```e.g. C:\Program Files\apache-maven-3.6.3```

To set the *PATH* variable, browse to **Control Panel** and then **System.** 
Click **Advanced** and then **Environment Variables.**
Add a new system variable using that path, with the name **M2_HOME** or something recognisable.
Finally, add **%M2_HOME%\bin** to the **PATH** variable.

To verify the Maven installation, launch a command prompt window and type:

```mvn -version```

If you don't get a successful response from the command, you will need to return to the installation process.

#### Eclipse

Go to this link [download Eclipse.] (https://www.eclipse.org/downloads/)
Download the X86_64 executable.
Run the .exe and complete the setup wizard to install Eclipse, default/java options are fine throughout.

#### Spring Tool Suit 4

Go to this link [Eclipse marketplace.] (https://marketplace.eclipse.org/content/spring-tools-4-aka-spring-tool-suite-4)
Drag and drop the "Install" button inside of a running eclipse.
Spring will be automatically installed.

#### MySQL Server

Go to this link [download MySQL.] (https://dev.mysql.com/downloads/windows/installer/8.0.html)
Download the *mysql-installer-community-8.0.24.0.msi*
Complete the setup wizard, making sure to select MySQL server.
All default options are fine, with a default password of **root** for the server.
If you use different login details, be sure to change the values in the application-prod.properties file in the project to connect to your database.

#### Web Driver for Selenium tests

Depending on the browser you use, you'll need a specific driver. 
For ease, and to ensure compatability, please use Google Chrome, which you can download [here.] (https://www.google.com/chrome/)
The tests written for Selenium in this project require Google Chrome, but feel free to refactor the code and use which ever driver you like.

Below is a list of various web drivers for different browsers:

Chrome/Chromium   - [Download](https://chromedriver.storage.googleapis.com/index.html)
Firefox           - [Download](https://chromedriver.storage.googleapis.com/index.html)
Edge              - [Download](https://chromedriver.storage.googleapis.com/index.html)
Internet Explorer - [Download](https://chromedriver.storage.googleapis.com/index.html)
Opera             - [Download](https://chromedriver.storage.googleapis.com/index.html)

Make sure to check your browser version and choose the appropriate driver.

Place the driver (e.g. chromedriver.exe) into the "src/test/resources" folder of the project once you have cloned it down to your local machine.

## Installing

A step by step series of examples that tell you how to get a development env running

### Clone the project from GitHub

Use the following command to clone this repository to your local machine:

```git clone https://github.com/ALowtonQA/ALowtonQA_assessment.git```

### Import the project to Eclipse as an existing Maven project

In Eclipse, choose *File > Import*.
Then, under **Maven** choose **Existing Maven Project**.
Select the previously imported repository as the root directory.
Ensure the *pom.xml* file is visible and selected, then click Import.

### Run the Application

To run the application from Eclipse, simply right click the project folder in the hierarchical view on the left and *Right click > Run as > Spring Boot app*

Once the server has started, you will be able to browse to http://localhost:8080 and begin interacting with the application.

By default, port 8080 is used by the application. If you need to set a different port, navigate to the application-prod.properties file in the "src/test/resources" folder.
Then, set the value of the server.port property:
*server.port=XXXX*

Also, if your MySQL database instance is running on a port other than 3306, be sure to change the "spring.datasource.url" value in the same file.

To run the application from your command line, browse to the root folder of the project using a command prompt.
Then execute the following command:

```java -jar MusicHWA-0.0.1-SNAPSHOT.jar```

## Running the tests

To run all of the different types of tests on the system, right click the project and *Run as > JUnit Test*

NOTE: If the tests don't run, it may be because you need to select JUnit 4 as the test runner.

To do this simply right click and *Run as > Run Configurations* then select *Junit 4* from the drop-down list.

If the selenium tests fail, ensure that the chromedriver.exe is the correct version and located in the "src/test/resources" folder.

Tests can also be ran individually.

### Unit Tests 

To test only the functionality of the MusicController class:
Browse to the *src/test/java/com.qa.musichwa.controller* package using the hierarchical view on the left.
Locate the *MusicControllerUnitTest* file and *Right click > Run as > JUnit Test*
This will unit test the Music Controller class in isolation, by testing each method functions correctly.

### Integration Tests 

To run an integration test:
Browse to the *src/test/java/com.qa.musichwa.controller* package using the hierarchical view on the left.
Locate the *MusicControllerIntegrationTest* file and *Right click > Run as > JUnit Test*
This will integration test the controller, service and repo layers of the application using a MockMVC to make API calls.

### Automated User-Acceptance tests

To run only the selenium tests:
Browse to the *src/test/java/com.qa.musichwa.selenium.tests* package using the hierarchical view on the left.
Locate the *IndexTest* file and *Right click > Run as > JUnit Test*
This will user-acceptance test the front-end, by automating interactions that a user would do.

## Project Management Board
* [Jira Board](https://anoushlowtonqa.atlassian.net/jira/software/projects/HWA/boards/4)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Inversion of control container

## Author

* [anoushlowton](https://github.com/ALowtonQA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments/References
* [Bootstrap](https://getbootstrap.com/docs/5.0/getting-started/introduction/) - CSS/JS
* [Axios](https://github.com/axios/axios) - HTTP Requests
* [QA](https://www.qa.com/) - website logo

