# suhilaahmed-calc-test-api

A Simple Equation Calculator Application in which a user can type a simple equation and submit.
## Getting Started
These instructions will get you a copy of the framework up and running up and running on your local machine for testing process.
### Prerequisites
Install the 1.8 JDK
```
[Oracle website](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html).
```
Install maven globally
```
[Apache Maven website](https://maven.apache.org/).
```
Install IntelliJ IDEA
```
[You can find it on jetBrains Website](https://www.jetbrains.com/idea/download/#section=mac).
```

## Running the tests

After cloning the framework you will need to run the following steps you need to avigate to equation calculator directory.
Then you need to open the equation calculator inside IntelliJ.
After the project is imported successfully.

### Run the unit tests and the integration tests
In the root directory of the project run the following command.
```
mvn test
```
### Test report
After the test suites are fully executed navigate to target folder and check reports under the surefire-reports directory.

## Starting the backend
### Get equation calculator up and running
In the root directory of the project run the following command.
```
mvn spring-boot:run
```

## Performance Tests

Navigate up from the project directory to the load_test directory.

### Prerequisites
Install python 3.9
```
[Python website](https://www.python.org/downloads/).
```
Install locust library
In the terminal run the following command.
```
pip3 install locust
```
Verify the locust has been installed successfully, In the terminal run the following command.
```
locust -V
```

### Run the performance tests
After locust has been installed successfully, in the terminal of the load_test directory run the following command.
```
locust --config=master.conf
```
### Test report
After the performance tests finished running you will find the log file named : locust_log.log created at the same directory.

## End to End Tests
### Prerequisites
Install postman.
Have the backend up and running as described above.

#### Navigate up from the project directory to the postman_endToEnd-tests directory.
Open postman.
Import the Equation_Calculator.postman_collection.json collection.
Click on Runner button and choose the imported collection.
Click on start run.

### Test report
After the postman collection is finished running you can find the report on the screen on postman console.

## CI
CI has been implmented using Jenkins on local environment.
### Prerequisites
Install Jenkins locally
Create a new pipeline item

#### In the root directory of the project you will find a jenkins file contains the deployment script
Copy and paste the file in pipeline script.
Hit save and Build now.

### Viola! You have everything up and running
