package com.pdv.springbootkafka.config;

import com.pdv.springbootkafka.model.request.PublishEmailRequest;
import com.pdv.springbootkafka.model.request.PublishNotificationRequest;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private Map<String, Object> initialProducerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return config;
    }

    /*Producer*/
    @Bean
    public ProducerFactory<String, PublishEmailRequest> producerEmailFactory() {
        return new DefaultKafkaProducerFactory<>(initialProducerFactory());
    }

    @Bean
    public ProducerFactory<String, PublishNotificationRequest> producerNotificationFactory() {
        return new DefaultKafkaProducerFactory<>(initialProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, PublishEmailRequest> emailKafkaTemplate() {
        return new KafkaTemplate<>(producerEmailFactory());
    }

    @Bean
    public KafkaTemplate<String, PublishNotificationRequest> notificationKafkaTemplate() {
        return new KafkaTemplate<>(producerNotificationFactory());
    }

    /*Consumer*/
    @Bean
    public ConsumerFactory<String, PublishEmailRequest> emailConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(initialProducerFactory(), new StringDeserializer(), new JsonDeserializer<>(PublishEmailRequest.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PublishEmailRequest> emailKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PublishEmailRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(emailConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, PublishNotificationRequest> notificationConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(initialProducerFactory(), new StringDeserializer(), new JsonDeserializer<>(PublishNotificationRequest.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PublishNotificationRequest> notificationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PublishNotificationRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(notificationConsumerFactory());
        return factory;
    }
}
