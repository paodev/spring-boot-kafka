package com.pdv.springbootkafka.service.consumer;

import com.pdv.springbootkafka.model.request.PublishEmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "consumer", name = "email.enabled")
public class EmailListener {

    @KafkaListener(topics = "topic-email", groupId = "development-group", containerFactory = "emailKafkaListenerContainerFactory")
    public void consume(PublishEmailRequest message) {
        log.info("Message: {}", message);
    }
}
