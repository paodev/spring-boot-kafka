# Spring Boot with Kafka Producer and Consumer Example
This Project covers how to use Spring Boot with Spring Kafka to Publish JSON/String message to a Kafka topic
## VM Options
- #### For Publish Producer
- `-Dserver.port=8080 -Dproducer.enabled=true`
- #### For Email Consumer
- `-Dserver.port=8081 -Dconsumer.email.enabled=true`
- #### For Notification Consumer
- `-Dserver.port=8082 -Dconsumer.notification.enabled=true`

# Install Apache Kafka on Docker
This source site [GitHub Pages](https://github.com/wurstmeister/kafka-docker).