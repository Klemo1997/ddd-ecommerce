package com.klemo.ecommerce.sales.catalog.service.application.rest;

import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.sales.catalog.service.domain.ListProducts;
import com.klemo.ecommerce.sales.catalog.service.domain.ProductDetails;
import com.klemo.ecommerce.sales.catalog.service.domain.ProductNotFoundException;
import com.klemo.ecommerce.sales.catalog.service.domain.dto.ListProductsQuery;
import com.klemo.ecommerce.sales.catalog.service.domain.dto.ListProductsResponse;
import com.klemo.ecommerce.sales.catalog.service.domain.dto.ProductDetailsQuery;
import com.klemo.ecommerce.sales.catalog.service.domain.dto.ProductDetailsResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/catalog", produces = "application/json")
public final class CatalogController {
    @NonNull private final ListProducts listProducts;
    @NonNull private final ProductDetails productDetails;

    @GetMapping("/products")
    public ResponseEntity<ListProductsResponse> listProducts(@RequestParam(defaultValue = "1") Integer page) {
        var query = new ListProductsQuery(page);
        ListProductsResponse response = listProducts.list(query);
        log.info("Returning products for query: {}", query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDetailsResponse> productDetails(@NonNull @PathVariable UUID productId) {
        var query = new ProductDetailsQuery(new ProductId(productId));
        try {
            ProductDetailsResponse response = productDetails.details(query);
            log.info("Returning product details for query: {}", query);
            return ResponseEntity.ok(response);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
