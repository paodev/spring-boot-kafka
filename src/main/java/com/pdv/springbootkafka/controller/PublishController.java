package com.pdv.springbootkafka.controller;

import com.pdv.springbootkafka.model.request.PublishEmailRequest;
import com.pdv.springbootkafka.model.request.PublishNotificationRequest;
import com.pdv.springbootkafka.service.producer.EmailProducer;
import com.pdv.springbootkafka.service.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "producer", name = "enabled")
@RequestMapping(value = "/v1/message/queue", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PublishController {

    private final EmailProducer emailProducer;
    private final NotificationProducer notificationProducer;

    @PostMapping(value = "/email/publish")
    public ResponseEntity publishEmail(@Valid @RequestBody PublishEmailRequest publishEmailRequest) {
        return ResponseEntity.ok(emailProducer.publish(publishEmailRequest));
    }

    @PostMapping(value = "/notification/publish")
    public ResponseEntity publishNotification(@Valid @RequestBody PublishNotificationRequest publishNotificationRequest) {
        return ResponseEntity.ok(notificationProducer.publish(publishNotificationRequest));
    }
}
