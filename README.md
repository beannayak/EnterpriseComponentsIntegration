# EnterpriseComponentsIntegration

## What is this project?
    This project is a demo for using spring boot with 
        1. Kafka
        2. Camunda
        3. JPA
        4. Service-to-service communication with GRPC
        5. FeignClient, with circuit breaker pattern

## How to run this project?
### Start Confluent platform
  **Download**: https://docs.confluent.io/platform/current/installation/installing_cp/zip-tar.html  
  **Start by using command**:  
         confluent_home$ export CONFLUENT_HOME=/home/<user_path>/confluent; export JAVA_HOME=/usr/lib/jvm/java-8-openjdk; ./bin/confluent local services start  
  **Add schema:**  
    By default control panel runs in 9021 port. Create a topic named "mytopic" and register Avro schema from the project's resouce folder.
         
### Add generated source path as source:
      Source Paths:
         /target/generated/avro
         /target/generated-sources/protobuf/grpc-java
         /target/generated-sources/protobuf/java
         
### Run Application
     Start main method from application class.
