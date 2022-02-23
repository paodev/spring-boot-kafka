package com.pdv.springbootkafka.service.consumer;

import com.pdv.springbootkafka.model.request.PublishNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "consumer", name = "notification.enabled")
public class NotificationListener {

    @KafkaListener(topics = "topic-notification", groupId = "development-group", containerFactory = "notificationKafkaListenerContainerFactory")
    public void consume(PublishNotificationRequest message) {
        log.info("Message: {}", message);
    }
}
