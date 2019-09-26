# order-validator-tutorial

Project used to teach how to integrate an spring boot application with AWS SQS.

# Requirements

- Java 11
- Maven
- AWS account for using SQS service

# Inserting AWS credentials configuration

* Go to application.properties e replace the amazon.accessKey and amazon.secretKey with your account credentials

# Running the project

Two ways to run the project

* mvn spring-boot:run
* mvn clean package && java -jar target/order-validator-0.0.1-SNAPSHOT.jar
