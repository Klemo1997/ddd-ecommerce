package com.klemo.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableJpaRepositories(basePackages = {
    "com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess",
    "com.klemo.ecommerce.sales.catalog.service.infrastructure.dataaccess",
    "com.klemo.ecommerce.sales.order.service.infrastructure.dataaccess",
    "com.klemo.ecommerce.warehouse.service.infrastructure.dataaccess"
})
@EntityScan(basePackages = {
    "com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess",
    "com.klemo.ecommerce.sales.catalog.service.infrastructure.dataaccess",
    "com.klemo.ecommerce.sales.order.service.infrastructure.dataaccess",
    "com.klemo.ecommerce.warehouse.service.infrastructure.dataaccess"
})
@SpringBootApplication(scanBasePackages = "com.klemo.ecommerce")
public class ECommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }
}
