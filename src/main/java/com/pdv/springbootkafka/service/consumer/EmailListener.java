package com.pdv.springbootkafka.service.consumer;

import com.pdv.springbootkafka.model.request.PublishEmailRequest;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "consumer", name = "email.enabled")
public class EmailListener {

  @Value("${consumer.email.sleep.enabled:false}")
  private boolean isSleep;

  @KafkaListener(topics = "topic-email", groupId = "development-group", containerFactory = "emailKafkaListenerContainerFactory")
  public void consume(PublishEmailRequest message) {
    if (isSleep && LocalDateTime.now().getSecond() % 2 == 0) {
      int sleepSeconds = ThreadLocalRandom.current().nextInt(1, 6); // สุ่ม 1-5 วินาที
      try {
        Thread.sleep(sleepSeconds * 1000L);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    log.info("Message: {}", message);
  }
}
