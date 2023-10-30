package com.klemo.ecommerce.sales.catalog.service.domain.dto;

import lombok.NonNull;

import java.util.List;

public record ListProductsResponse(@NonNull List<Product> products) {}
