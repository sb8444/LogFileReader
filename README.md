
<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#project-installation-and-execution">Project Installation and Execution</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

**LogFileReader** is a Spring Boot Application to Read Json Format logs from a separate File and store into HSQLDB

<p align="right">(<a href="#top">back to top</a>)</p>


### Built With

* Spring Boot
* Java 8
* HSQL
* Spring Data JPA

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started
To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* JAVA 8 SDK
* Intellij IDEA /Eclipse/ Any other Java IDE
* Maven
* POSTMAN or Google Chrome

### Project Installation and Execution

1. Open a Java IDE and clone and import the below git repo as a Maven Project
2. Clone the repo
   ```sh
   git clone https://github.com/sb8444/LogFileReader.git
   ```
3. Go to the below path and add/modify the logFile.txt as per the given format
    ```sh
   LogFileReader\src\main\resources\logFile.txt
   ```
   ![logFile](https://user-images.githubusercontent.com/31707485/144312142-03dc4948-3dfc-4b76-acdd-9aa146f91b71.JPG)

   
4. Right Click on the **LogFileReaderApplication** Class and Run the Application as a Java/Spring Boot Application

    ![StartApplication](https://user-images.githubusercontent.com/31707485/144312594-bd54ae4a-7c50-4433-85bc-a2d17bd826f8.JPG)

5. Once the Application is started, you will see application logs like below

    ![image](https://user-images.githubusercontent.com/31707485/144312859-41040a92-0732-4702-b318-2706337c4482.png)

6. Inorder to view the testDb in HSQL, you need to paste the below URL in Google Chrome or POSTMAN(Using Get Method) 
    ```sh
    http://localhost:8080/viewAllLogs
     ```
7. You will see the output from HSQl DB in the below format

    ![image](https://user-images.githubusercontent.com/31707485/144313481-c519997d-e668-42d6-b355-c0bcd5672d62.png)


<p align="right">(<a href="#top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

The Application Reads logs from logFile.txt File under \src\main\resources\ folder and inserts them into HSQL Memory DB



<!-- ROADMAP -->
## Roadmap

- [] Addition of Multithreading
- [] Addition of Junit Test Cases


<!-- CONTACT -->
## Contact

Your Name - [@Linkedin](https://www.linkedin.com/in/sunandan-bhakat/) 

Email: sunandan.bhakat2011@gmail.com

Project Link: [https://github.com/sb8444/LogFileReader](https://github.com/sb8444/LogFileReader)

<p align="right">(<a href="#top">back to top</a>)</p>




