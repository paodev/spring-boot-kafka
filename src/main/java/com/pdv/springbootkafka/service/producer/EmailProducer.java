package com.pdv.springbootkafka.service.producer;

import com.pdv.springbootkafka.model.request.PublishEmailRequest;
import com.pdv.springbootkafka.model.response.ResponseModel;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailProducer {

  private final String TOPIC_EMAIL = "topic-email";

  private final KafkaTemplate<String, PublishEmailRequest> kafkaTemplate;

  public ResponseModel publish(PublishEmailRequest publishEmailRequest) {
    ResponseModel responseModel = new ResponseModel();
    /*Make sure messages are distributed to other partitions, not just the same one.*/
    int randomKey = ThreadLocalRandom.current().nextInt(1, 4); // สุ่ม 1-3 วินาที
    kafkaTemplate.send(TOPIC_EMAIL, String.valueOf(randomKey), publishEmailRequest)
        .whenComplete((result, ex) -> {
          if (ex != null) {
            log.error("Unable to send message=[{}] due to: {}",
                publishEmailRequest,
                ex.getMessage()
            );
            responseModel.unsuccessful();
          } else {
            log.info("Sent message=[{}] with offset=[{}]",
                publishEmailRequest,
                result.getRecordMetadata().offset()
            );
          }
        });

    return responseModel;
  }
}
