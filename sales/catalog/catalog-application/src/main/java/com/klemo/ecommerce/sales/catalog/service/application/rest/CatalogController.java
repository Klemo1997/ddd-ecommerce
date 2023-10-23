package com.klemo.ecommerce.sales.catalog.service.application.rest;

import com.klemo.ecommerce.sales.catalog.service.domain.ListProducts;
import com.klemo.ecommerce.sales.catalog.service.domain.dto.ListProductsQuery;
import com.klemo.ecommerce.sales.catalog.service.domain.dto.ListProductsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/catalog", produces = "application/json")
public final class CatalogController {
    private final ListProducts listProducts;

    @GetMapping("/products")
    public ResponseEntity<ListProductsResponse> listProducts(@RequestParam(defaultValue = "1") Integer page) {
        var query = new ListProductsQuery(page);
        ListProductsResponse response = listProducts.list(query);
        log.info("Returning products for query: {}", query);
        return ResponseEntity.ok(response);
    }
}
