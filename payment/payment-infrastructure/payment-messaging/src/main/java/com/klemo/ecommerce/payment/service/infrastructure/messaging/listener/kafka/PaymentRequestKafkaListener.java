package com.klemo.ecommerce.payment.service.infrastructure.messaging.listener.kafka;

import com.klemo.ecommerce.infrastructure.kafka.consumer.KafkaConsumer;
import com.klemo.ecommerce.infrastructure.kafka.model.PaymentRequestModel;
import com.klemo.ecommerce.payment.service.domain.dto.PaymentRequest;
import com.klemo.ecommerce.payment.service.domain.port.input.PaymentRequestMessageListener;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRequestKafkaListener implements KafkaConsumer<PaymentRequestModel> {
    @NonNull private final PaymentRequestMessageListener paymentRequestMessageListener;

    @Override
    @KafkaListener(topics = "${kafka.payment-request-topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void receive(
            @Payload PaymentRequestModel data,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer receivedPartition,
            @Header(KafkaHeaders.OFFSET) Long offset) {
        log.info("consuming data: {}, key: {}, partition: {}, offset: {}", data, key, receivedPartition, offset);
        paymentRequestMessageListener.completePayment(paymentRequestFrom(data));
    }

    private PaymentRequest paymentRequestFrom(PaymentRequestModel message) {
        return new PaymentRequest(
                UUID.fromString(message.id()),
                UUID.fromString(message.orderId()),
                message.price(),
                message.createdAt()
        );
    }
}
