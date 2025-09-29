package com.pdv.springbootkafka.service.producer;

import com.pdv.springbootkafka.model.request.PublishNotificationRequest;
import com.pdv.springbootkafka.model.response.ResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProducer {

  private final String TOPIC_NOTIFICATION = "topic-notification";

  private final KafkaTemplate<String, PublishNotificationRequest> kafkaTemplate;

  public ResponseModel publish(PublishNotificationRequest publishNotificationRequest) {
    ResponseModel responseModel = new ResponseModel();
    kafkaTemplate.send(TOPIC_NOTIFICATION, publishNotificationRequest)
        .whenComplete((result, ex) -> {
          if (ex != null) {
            log.error("Unable to send message=[{}] due to: {}",
                publishNotificationRequest,
                ex.getMessage()
            );
            responseModel.unsuccessful();
          } else {
            log.info("Sent message=[{}] with offset=[{}]",
                publishNotificationRequest,
                result.getRecordMetadata().offset()
            );
          }
        });

    return responseModel;
  }
}
