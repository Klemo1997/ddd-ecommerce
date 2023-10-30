package com.klemo.ecommerce.sales.order.service.domain.value_object;

import java.util.UUID;

public record StreetAddress(UUID id, String street, String postalCode, String city) {
}
