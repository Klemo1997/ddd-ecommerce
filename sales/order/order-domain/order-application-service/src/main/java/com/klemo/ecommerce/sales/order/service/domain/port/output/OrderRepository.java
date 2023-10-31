package com.klemo.ecommerce.sales.order.service.domain.port.output;

import com.klemo.ecommerce.sales.order.service.domain.entity.Order;

public interface OrderRepository {
    Order save(Order order);
}
