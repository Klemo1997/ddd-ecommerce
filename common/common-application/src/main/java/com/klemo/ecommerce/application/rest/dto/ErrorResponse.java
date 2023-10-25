package com.klemo.ecommerce.application.rest.dto;

import lombok.NonNull;

public record ErrorResponse(@NonNull String message) {
    public static ErrorResponse of(String message) {
        return new ErrorResponse(message);
    }
}
