package com.klemo.ecommerce.sales.order.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean OrderPriceCalculator orderPriceCalculator() {
        return new OrderPriceCalculator();
    }
}
