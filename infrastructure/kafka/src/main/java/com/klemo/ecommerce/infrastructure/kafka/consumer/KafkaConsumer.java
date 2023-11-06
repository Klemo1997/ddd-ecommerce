package com.klemo.ecommerce.infrastructure.kafka.consumer;

public interface KafkaConsumer<T> {
    void receive(T message, String key, Integer partition, Long offset);
}
