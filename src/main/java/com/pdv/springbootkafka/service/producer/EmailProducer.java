package com.pdv.springbootkafka.service.producer;

import com.pdv.springbootkafka.model.request.PublishEmailRequest;
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
public class EmailProducer {

    private final String TOPIC_EMAIL = "topic-email";

    private final KafkaTemplate<String, PublishEmailRequest> kafkaTemplate;

    public ResponseModel publish(PublishEmailRequest publishEmailRequest) {
        ResponseModel responseModel = new ResponseModel();
        kafkaTemplate.send(TOPIC_EMAIL, publishEmailRequest).addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, PublishEmailRequest> result) {
                log.info("Sent message=[{}] with offset=[{}]", publishEmailRequest, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[{}] due to : {}", publishEmailRequest, ex.getMessage());
                responseModel.unsuccessful();
            }
        });
        return responseModel;
    }
}
