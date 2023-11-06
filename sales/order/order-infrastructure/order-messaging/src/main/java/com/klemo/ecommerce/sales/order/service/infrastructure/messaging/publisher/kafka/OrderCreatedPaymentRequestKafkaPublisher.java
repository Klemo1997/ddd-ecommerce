package com.klemo.ecommerce.sales.order.service.infrastructure.messaging.publisher.kafka;

import com.klemo.ecommerce.domain.value_object.Money;
import com.klemo.ecommerce.infrastructure.kafka.config.KafkaConfig;
import com.klemo.ecommerce.infrastructure.kafka.model.PaymentRequestModel;
import com.klemo.ecommerce.sales.order.service.domain.OrderPriceCalculator;
import com.klemo.ecommerce.sales.order.service.domain.dto.OrderCreatedEvent;
import com.klemo.ecommerce.sales.order.service.domain.port.output.OrderCreatedPaymentRequestEventPublisher;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreatedPaymentRequestKafkaPublisher implements OrderCreatedPaymentRequestEventPublisher {
    @NonNull private final KafkaTemplate<String, PaymentRequestModel> kafkaProducer;
    @NonNull private final KafkaConfig kafkaConfig;
    @NonNull private final OrderPriceCalculator orderPriceCalculator;

    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().toString();
        log.info("Received {} for order id: {}", OrderCreatedEvent.class.getName(), orderId);

        try {
            PaymentRequestModel paymentRequestModel = paymentRequestModelFromOrderCreatedEvent(domainEvent);

            kafkaProducer.send(
                    kafkaConfig.getPaymentRequestTopicName(),
                    orderId,
                    paymentRequestModel
            ).whenComplete((result, ex) -> {
                if (ex == null) {
                    RecordMetadata metadata = result.getRecordMetadata();
                    log.info("Received successful response from Kafka for order id: {}"
                                    + " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                            orderId,
                            metadata.topic(),
                            metadata.partition(),
                            metadata.offset(),
                            metadata.timestamp());
                } else {
                    log.error("Error while sending " + PaymentRequestModel.class.getName()
                            + " message {} to topic {}", paymentRequestModel, kafkaConfig.getPaymentRequestTopicName(), ex);
                }
            });
        } catch (Exception ex) {
            log.error("Error while sending {} message to kafka with order id: {}, error: {}",
                    PaymentRequestModel.class.getName(), orderId, ex.getMessage());
        }
    }

    private PaymentRequestModel paymentRequestModelFromOrderCreatedEvent(OrderCreatedEvent domainEvent) {
        Money totalPrice = orderPriceCalculator.getTotal(domainEvent.getOrder());

        return new PaymentRequestModel(
                UUID.randomUUID().toString(),
                domainEvent.getOrder().getId().toString(),
                totalPrice.amount(),
                totalPrice.currency().getCurrencyCode(),
                domainEvent.getCreatedAt().toInstant());
    }
}
