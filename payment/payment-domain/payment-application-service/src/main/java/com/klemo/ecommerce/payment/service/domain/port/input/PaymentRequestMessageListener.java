package com.klemo.ecommerce.payment.service.domain.port.input;

import com.klemo.ecommerce.payment.service.domain.dto.PaymentRequest;

public interface PaymentRequestMessageListener {
    void completePayment(PaymentRequest paymentRequest);
}
