package com.geli.warehouse.presentation.request;

public record UpdateVariantRequest(
                String category,
                String name,
                Boolean isActive) {
}
