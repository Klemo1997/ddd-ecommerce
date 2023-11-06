package com.klemo.ecommerce.infrastructure.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${kafka.payment-request-topic-name}")
    private String paymentRequestTopicName;

    public String getBootstrapAddress() {
        return bootstrapAddress;
    }

    public String getPaymentRequestTopicName() {
        return paymentRequestTopicName;
    }
}
