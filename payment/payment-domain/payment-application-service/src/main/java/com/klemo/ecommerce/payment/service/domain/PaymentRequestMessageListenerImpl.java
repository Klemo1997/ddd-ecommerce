package com.klemo.ecommerce.payment.service.domain;

import com.klemo.ecommerce.payment.service.domain.dto.PaymentRequest;
import com.klemo.ecommerce.payment.service.domain.port.input.PaymentRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {
    @Override
    public void completePayment(PaymentRequest paymentRequest) {
        log.info("Payment request received: {}", paymentRequest);
    }
}
