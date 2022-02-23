package com.pdv.springbootkafka.service.producer;

import com.pdv.springbootkafka.model.request.PublishNotificationRequest;
import com.pdv.springbootkafka.model.response.ResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final String TOPIC_NOTIFICATION = "topic-notification";

    private final KafkaTemplate<String, PublishNotificationRequest> kafkaTemplate;

    public ResponseModel publish(PublishNotificationRequest publishNotificationRequest) {
        ResponseModel responseModel = new ResponseModel();
        kafkaTemplate.send(TOPIC_NOTIFICATION, publishNotificationRequest).addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, PublishNotificationRequest> result) {
                log.info("Sent message=[{}] with offset=[{}]", publishNotificationRequest, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[{}] due to : {}", publishNotificationRequest, ex.getMessage());
                responseModel.unsuccessful();
            }
        });
        return responseModel;
    }
}
