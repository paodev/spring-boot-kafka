# Spring Boot with Kafka Producer and Consumer Example
This Project covers how to use Spring Boot with Spring Kafka to Publish JSON/String message to a Kafka topic
## VM Options
- #### For Publish Producer
- `-Dserver.port=8080 -Dproducer.enabled=true`
- #### For Email Consumer
- `-Dserver.port=8081 -Dconsumer.email.enabled=true`
- #### For Notification Consumer
- `-Dserver.port=8082 -Dconsumer.notification.enabled=true`

## cURL
```shell
curl --silent --location --request POST 'http://localhost:8080/v1/message/queue/email/publish' \
--header 'request-id: 7a793531-f81e-461c-9921-346e05f924ff' \
--header 'Content-Type: application/json' \
--data-raw '{
    "to": "abc@email.com",
    "payload": "Hi Kafka"
}'
```

```shell
curl --silent --location --request POST 'http://localhost:8080/v1/message/queue/notification/publish' \
--header 'request-id: f48b906e-5773-4508-af1e-2d014578b8a1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "token": "8c7286bb-0143-471b-893d-c0047ab15283",
    "deviceId": "c52eb26c-bcfc-4172-b0eb-c5f5d63e403a",
    "message": "Hi Kafka"
}'
```
# Install Apache Kafka on Docker
This source site [GitHub Pages](https://github.com/wurstmeister/kafka-docker).