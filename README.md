# suhilaahmed-calc-test-api

a Simple Equation Calculator Application in which a user can type a simple equation and submit.
## Getting Started
These instructions will get you a copy of the framework up and running up and running on your local machine for testing process.
### Prerequisites
Install the 1.8 JDK
```
[https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html] Oracle website.
```
Install maven globally
```
[https://maven.apache.org/] Apache Maven website.
```
Install IntelliJ IDEA
```
[https://www.jetbrains.com/idea/download/#section=mac] You can find it on jetBrains Website.
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

## Performance Test

Navigate up from the project directory to the ###load_test directory.

### Prerequisites
Install python 3.9
```
[https://www.python.org/downloads/] Python website.
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

